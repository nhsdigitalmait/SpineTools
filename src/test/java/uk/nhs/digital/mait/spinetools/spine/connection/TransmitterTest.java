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
package uk.nhs.digital.mait.spinetools.spine.connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import uk.nhs.digital.mait.spinetools.spine.messaging.Sendable;
import uk.nhs.digital.mait.spinetools.spine.messaging.SpineHL7Message;
import uk.nhs.digital.mait.spinetools.spine.messaging.SpineSOAPRequest;

/**
 *
 * @author simonfarrow
 */
public class TransmitterTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private static final String MY_IP_ADDRESS = "127.0.0.1";
    private static final String MY_PARTY_KEY = "YEA-801248";
    private static final String MY_ASID = "SIAB-001";

    private static final String SVCID = "urn:nhs:names:services:itk:COPC_IN000001GB01";
    private static final String ORG_ID = "YEA";

    public TransmitterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("org.warlock.spine.sds.cachedir", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/transmitter_source/cache");
        System.setProperty("org.warlock.spine.sds.myasid", MY_ASID);
        System.setProperty("org.warlock.spine.sds.mypartykey", MY_PARTY_KEY);

        System.setProperty("org.warlock.http.spine.certs", System.getenv("TKWROOT") + "/contrib/Test_Certificates/TLS_Test_Certificates/Test01/test01.jks");
        System.setProperty("org.warlock.http.spine.sslcontextpass", "test01tls_moscow");

        System.setProperty("org.warlock.spine.connection.myip", MY_IP_ADDRESS);

        System.setProperty("org.warlock.spine.sds.url", "http://localhost");
        System.setProperty("org.warlock.spine.sds.urlresolver", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/urlresolver.txt");

        System.setProperty("org.warlock.spine.messaging.sessioncaptureclass", "uk.nhs.digital.mait.spinetools.spine.connection.BasicSessionCaptor");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class Transmitter.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testRun() throws InterruptedException, Exception {
        System.out.println("run");
        SdsTransmissionDetails c = ConnectionManager.getInstance().getTransmissionDetails(SVCID, ORG_ID, null, null).get(0);
        String hl7payload = "hl7payload";
        SpineHL7Message m = new SpineHL7Message(SVCID, hl7payload);
        Sendable sendable = new SpineSOAPRequest(c, m);
        Transmitter instance = new Transmitter(sendable);
        instance.start();
        instance.join();
    }

}
