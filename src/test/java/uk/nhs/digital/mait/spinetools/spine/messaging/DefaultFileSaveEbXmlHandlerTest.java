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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;

/**
 *
 * @author simonfarrow
 */
public class DefaultFileSaveEbXmlHandlerTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    public DefaultFileSaveEbXmlHandlerTest() {
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
        
        System.setProperty("org.warlock.spine.messaging.defaultebxmlhandler.filesavedirectory", "test");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        for ( File file : new File("test").listFiles()) {
            if ( file.getName().endsWith(".message")) {
                System.out.println(file.getName());
                file.delete();
            }
        }
    }

    /**
     * Test of handle method, of class DefaultFileSaveEbXmlHandler.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testHandle() throws Exception {
        System.out.println("handle");
        FileInputStream fis = new FileInputStream(System.getenv("TKWROOT") + "/contrib/SPINE_Test_Messages/MTH_Test_Messages/PDS2008A_Example_Input_Msg/PRPA_IN000203UK03_MCCI_IN010000UK13_1658.xml");
        EbXmlMessage m = new EbXmlMessage(fis);
        DefaultFileSaveEbXmlHandler instance = new DefaultFileSaveEbXmlHandler();
        instance.handle(m);
        // TODO
        // creates a .message file in test containg the DE
    }

}
