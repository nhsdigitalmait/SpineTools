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
// $Id: Edifact2xmlSuite.java 176 2017-02-28 15:55:23Z sfarrow $
package uk.nhs.digital.mait.spinetools.spine.messaging;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import uk.nhs.digital.mait.spinetools.spine.connection.ConnectionManager;
import uk.nhs.digital.mait.spinetools.spine.connection.SdsTransmissionDetails;
import uk.nhs.digital.mait.spinetools.spine.connection.ConnectionManager;
import uk.nhs.digital.mait.spinetools.spine.connection.SdsTransmissionDetails;

/**
 *
 * @author simonfarrow
 */
public class DefaultFileSaveSynchronousResponseHandlerTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private static File saved_messages_directory;

    public DefaultFileSaveSynchronousResponseHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("org.warlock.spine.sds.cachedir", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/transmitter_source/cache");
        System.setProperty("org.warlock.spine.sds.myasid", "SIAB-001");
        System.setProperty("org.warlock.spine.sds.mypartykey", "YEA-801248");

        System.setProperty("org.warlock.http.spine.certs", System.getenv("TKWROOT") + "/config/ITK_Correspondence/certs/tls.jks");
        System.setProperty("org.warlock.http.spine.sslcontextpass", "password");

        System.setProperty("org.warlock.spine.connection.myip", "127.0.0.1");

        System.setProperty("org.warlock.spine.sds.url", "http://localhost");
        System.setProperty("org.warlock.spine.sds.urlresolver", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/urlresolver.txt");

        System.setProperty("org.warlock.spine.messaging.defaultsynchronousresponsehandler.filesavedirectory", "test/saved_messages_directory");

        saved_messages_directory = new File(System.getProperty("org.warlock.spine.messaging.defaultsynchronousresponsehandler.filesavedirectory"));

        saved_messages_directory.mkdirs();
    }

    @AfterClass
    public static void tearDownClass() {
        saved_messages_directory.delete();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        for (File file : saved_messages_directory.listFiles()) {
            if ( file.getName().endsWith(".message")){
                System.out.println(file.getName());
                file.delete();
            }
        }
    }

    /**
     * Test of handle method, of class
     * DefaultFileSaveSynchronousResponseHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testHandle() throws Exception {
        System.out.println("handle");
        SdsTransmissionDetails c = ConnectionManager.getInstance().getTransmissionDetails("urn:nhs:names:services:itk:COPC_IN000001GB01", "YEA", null, null).get(0);
        SpineHL7Message m = new SpineHL7Message("id", "");
        SpineSOAPRequest r = new SpineSOAPRequest(c, m);
        r.soapAction = "soapaction";
        r.setSynchronousResponse("synchronousresponse");

        DefaultFileSaveSynchronousResponseHandler instance = new DefaultFileSaveSynchronousResponseHandler();
        instance.handle(r);
        // TODO creates a file in the saved_messages_directory containing the synchronous response text
    }

}
