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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import uk.nhs.digital.mait.distributionenvelopetools.itk.distributionenvelope.DistributionEnvelope;
import uk.nhs.digital.mait.distributionenvelopetools.itk.distributionenvelope.DistributionEnvelopeHelper;

/**
 *
 * @author simonfarrow
 */
public class ITKTrunkHandlerTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private ITKTrunkHandler instance;

    private static final String SAVED_MESSAGES_FOLDER_NAME = "src/test/resources/savedmessages";

    private static final File SAVED_MESSAGES_FOLDER = new File(SAVED_MESSAGES_FOLDER_NAME);

    public ITKTrunkHandlerTest() {
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

        System.setProperty("uk.nhs.digital.mait.spinetools.spine.messaging.defaultdistributionenvelopehandler.filesavedirectory", SAVED_MESSAGES_FOLDER_NAME);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tidyLogs();
        instance = new ITKTrunkHandler();
    }

    private void tidyLogs() {
        if (SAVED_MESSAGES_FOLDER.exists()) {
            for (File f : SAVED_MESSAGES_FOLDER.listFiles()) {
                f.delete();
            }
            SAVED_MESSAGES_FOLDER.delete();
        }
    }

    @After
    public void tearDown() {
        tidyLogs();
    }

    /**
     * Test of addHandler method, of class ITKTrunkHandler.
     */
    @Test
    public void testAddHandler() {
        System.out.println("addHandler");
        String s = "servicename";
        DistributionEnvelopeHandler h = (DistributionEnvelope d) -> {
        };
        instance.addHandler(s, h);
    }

    /**
     * Test of handle method, of class ITKTrunkHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testHandle() throws Exception {
        System.out.println("handle");
        FileInputStream fis = new FileInputStream("src/test/resources/data/ITK_Trunk.message");
        EbXmlMessage m = new EbXmlMessage(fis);
        InputStream is = new FileInputStream(System.getenv("TKWROOT") + "/contrib/ITK_2_01_Test_Messages/Correspondence_DE/Ambulance/POCD_MT030001UK01_DIST_Primary.xml");
        DistributionEnvelope de = DistributionEnvelopeHelper.getInstance().getDistributionEnvelope(is);
        ITKDistributionEnvelopeAttachment attachment = new ITKDistributionEnvelopeAttachment(de);
        m.addAttachment(attachment);
        instance.handle(m);

        assertTrue(SAVED_MESSAGES_FOLDER.exists());
        int expResult = 1;
        assertEquals(expResult, SAVED_MESSAGES_FOLDER.list().length);
    }

}
