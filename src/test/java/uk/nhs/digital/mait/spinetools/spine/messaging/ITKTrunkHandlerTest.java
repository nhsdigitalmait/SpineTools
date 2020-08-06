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

    public ITKTrunkHandlerTest() {
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
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new ITKTrunkHandler();
    }

    @After
    public void tearDown() {
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
    }

}
