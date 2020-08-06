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

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import uk.nhs.digital.mait.spinetools.spine.messaging.EbXmlMessage;

/**
 *
 * @author simonfarrow
 */
public class ListenerTest {


    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private Listener instance;

    private static EbXmlMessage ebXmlMessage;

    public ListenerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws FileNotFoundException, Exception {
        System.setProperty("org.warlock.spine.sds.cachedir", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/transmitter_source/cache");
        System.setProperty("org.warlock.spine.sds.myasid", "SIAB-001");
        System.setProperty("org.warlock.spine.sds.mypartykey", "YEA-801248");

        System.setProperty("org.warlock.http.spine.certs", System.getenv("TKWROOT") + "/config/ITK_Correspondence/certs/tls.jks");
        System.setProperty("org.warlock.http.spine.sslcontextpass", "password");

        System.setProperty("org.warlock.spine.connection.myip", "127.0.0.1");

        System.setProperty("org.warlock.spine.sds.url", "http://localhost");
        
        System.setProperty("org.warlock.spine.sds.urlresolver", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/urlresolver.txt");
        FileInputStream fis = new FileInputStream(System.getenv("TKWROOT") + "/contrib/SPINE_Test_Messages/MTH_Test_Messages/PDS2008A_Example_Input_Msg/PRPA_IN000203UK03_MCCI_IN010000UK13_1658.xml");
        ebXmlMessage = new EbXmlMessage(fis);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            instance = new Listener();
        } catch (Exception ex) {
            Logger.getLogger(ListenerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
        if (instance.isListening()) {
            instance.stopListening();
        }
    }

    /**
     * Test of setPersistDurations method, of class Listener.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetPersistDurations() throws Exception {
        System.out.println("setPersistDurations");
        HashMap<String, Long> p = new HashMap<>();
        p.put("urn:nhs:names:services:pds:PRPA_IN000203UK03", 99L);
        instance.setPersistDurations(p);
    }

    /**
     * Test of setPort method, of class Listener.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetPort() throws Exception {
        System.out.println("setPort");
        int p = 4000;
        instance.setPort(p);
    }

    /**
     * Test of getPort method, of class Listener.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPort() throws Exception {
        System.out.println("getPort");
        int expResult = 4430;
        int result = instance.getPort();
        assertEquals(expResult, result);
    }

    /**
     * Test of isListening method, of class Listener.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testIsListening() throws Exception {
        System.out.println("isListening");
        boolean expResult = false;
        boolean result = instance.isListening();
        assertEquals(expResult, result);
    }

    /**
     * Test of cleanDeduplicationList method, of class Listener.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCleanDeduplicationList() throws Exception {
        System.out.println("cleanDeduplicationList");

        HashMap<String, Long> p = new HashMap<>();
        p.put("urn:nhs:names:services:pds:PRPA_IN000203UK03", 1L); // 1 second
        instance.setPersistDurations(p);

        instance.receiveId(ebXmlMessage);
        // its now a dupe
        instance.receiveId(ebXmlMessage);
        
        // wait until the persist duration has been exceeded
        Thread.sleep(1500);

        // expired dupe is now cleared out
        instance.cleanDeduplicationList();

        // after cleanDeduplicationList its reported as not a dupe again
        boolean expResult = false; // message is a duplicate
        boolean result = instance.receiveId(ebXmlMessage);
        assertEquals(expResult, result);
    }

    /**
     * Test of receiveId method, of class Listener.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testReceiveId() throws Exception {
        System.out.println("receiveId");

        HashMap<String, Long> p = new HashMap<>();
        p.put("urn:nhs:names:services:pds:PRPA_IN000203UK03", 99L); // 99 s
        instance.setPersistDurations(p);

        boolean expResult = false; // message not a duplicate
        boolean result = instance.receiveId(ebXmlMessage);
        assertEquals(expResult, result);

        // second time through the received ids attribute is populated
        expResult = true; // message is a duplicate
        result = instance.receiveId(ebXmlMessage);
        assertEquals(expResult, result);
    }

    /**
     * Test of stopListening method, of class Listener.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testStopListening() throws Exception {
        System.out.println("stopListening");
        SocketAddress a = new InetSocketAddress("localhost", 4000);
        instance.startListening(a);
        assertTrue(instance.isListening());

        instance.stopListening();
        assertFalse(instance.isListening());
    }

    /**
     * Test of startListening method, of class Listener.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testStartListening() throws Exception {
        System.out.println("startListening");
        SocketAddress a = new InetSocketAddress("localhost", 4000);
        instance.startListening(a);
        assertTrue(instance.isListening());
    }

    /**
     * Test of run method, of class Listener.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRun() throws Exception {
        System.out.println("run");
        // called indirectly by the start in startListening
        //instance.run();
    }

}
