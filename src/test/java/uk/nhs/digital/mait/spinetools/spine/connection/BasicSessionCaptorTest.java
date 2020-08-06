/*
 Copyright 2016  Simon Farrow <simon.farrow1@hscic.gov.uk>

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package uk.nhs.digital.mait.spinetools.spine.connection;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import uk.nhs.digital.mait.spinetools.spine.messaging.Sendable;
import uk.nhs.digital.mait.spinetools.spine.messaging.SpineHL7Message;
import uk.nhs.digital.mait.spinetools.spine.messaging.SpineSOAPRequest;

/**
 *
 * @author simonfarrow
 */
public class BasicSessionCaptorTest {
    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    public BasicSessionCaptorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.setProperty("org.warlock.spine.sds.cachedir", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/transmitter_source/cache");
        System.setProperty("org.warlock.spine.sds.myasid", "SIAB-001");
        System.setProperty("org.warlock.spine.sds.mypartykey", "YEA-801248");

        System.setProperty("org.warlock.spine.messaging.BasicSessionCaptor.directory", "test");
        System.setProperty("org.warlock.spine.messaging.BasicSessionCaptor.extension", ".log");

        deleteLogs();

    }

    private void deleteLogs() {
        File folder = new File("test");
        for (File file : folder.listFiles((File dir, final String name) -> name.endsWith(".log"))) {
            file.delete();
        }
    }

    private void listLogs() {
        File folder = new File("test");
        for (File file : folder.listFiles((File dir, final String name) -> name.endsWith(".log"))) {
            System.out.println(file.getName());
        }
    }

    @After
    public void tearDown() {
        deleteLogs();
    }

    /**
     * Test of capture method, of class BasicSessionCaptor.
     */
    @Test
    public void testCapture() {
        try {
            System.out.println("capture");
            SDSSpineEndpointResolver resolver = new SDSSpineEndpointResolver();
            String odsCode = "YEA";
            ArrayList<SdsTransmissionDetails> details = resolver.getTransmissionDetails("urn:nhs:names:services:itk:COPC_IN000001GB01", odsCode, null, null);
            SdsTransmissionDetails std = details.get(0);

            SpineHL7Message m = new SpineHL7Message("id", "");
            Sendable s = new SpineSOAPRequest(std, m);
            s.setOnTheWireRequest("request".getBytes());
            s.setOnTheWireResponse("response".getBytes());

            BasicSessionCaptor instance = new BasicSessionCaptor();
            instance.capture(s);
            listLogs();
        } catch (Exception ex) {
            Logger.getLogger(BasicSessionCaptorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
