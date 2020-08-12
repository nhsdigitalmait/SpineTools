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
package uk.nhs.digital.mait.spinetools.spine.messaging;

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

/**
 *
 * @author simonfarrow
 */
public class SynchronousResponseHandlerTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    public SynchronousResponseHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("uk.nhs.digital.mait.spinetools.spine.sds.cachedir", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/transmitter_source/cache");
        System.setProperty("uk.nhs.digital.mait.spinetools.spine.sds.myasid", "SIAB-001");
        System.setProperty("uk.nhs.digital.mait.spinetools.spine.sds.mypartykey", "YEA-801248");

        System.setProperty("uk.nhs.digital.mait.spinetools.http.spine.certs", System.getenv("TKWROOT") + "/config/ITK_Correspondence/certs/tls.jks");
        System.setProperty("uk.nhs.digital.mait.spinetools.http.spine.sslcontextpass", "password");

        System.setProperty("uk.nhs.digital.mait.spinetools.spine.connection.myip", "127.0.0.1");

        System.setProperty("uk.nhs.digital.mait.spinetools.spine.sds.url", "http://localhost");
        System.setProperty("uk.nhs.digital.mait.spinetools.spine.sds.urlresolver", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/urlresolver.txt");
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
     * Test of handle method, of class SynchronousResponseHandler.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testHandle() throws Exception {
        System.out.println("handle");
        SdsTransmissionDetails c = ConnectionManager.getInstance().getTransmissionDetails("urn:nhs:names:services:itk:COPC_IN000001GB01", "YEA", null, null).get(0);
        SpineHL7Message m = new SpineHL7Message("id", "");
        SpineSOAPRequest r = new SpineSOAPRequest(c, m);

        SynchronousResponseHandler instance = new SynchronousResponseHandlerImpl();
        instance.handle(r);
    }

    public class SynchronousResponseHandlerImpl implements SynchronousResponseHandler {

        @Override
        public void handle(SpineSOAPRequest r) throws Exception {
            System.out.println("Handler called");
        }
    }

}
