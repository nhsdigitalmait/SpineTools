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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
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
public class EbXmlAcknowledgmentTest {
    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private EbXmlAcknowledgment instance;

    public EbXmlAcknowledgmentTest() {
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
        instance = new EbXmlAcknowledgment("ack");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of persist method, of class EbXmlAcknowledgment.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        // coded as empty block
        instance.persist();
    }

    /**
     * Test of write method, of class EbXmlAcknowledgment.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite() throws Exception {
        System.out.println("write");
        OutputStream s = new ByteArrayOutputStream(); 
        instance.write(s);
        String expResult = "ack";
        assertTrue(s.toString().contains(expResult));
    }

    /**
     * Test of getAckedMessageId method, of class EbXmlAcknowledgment.
     */
    @Test
    public void testGetAckedMessageId() {
        System.out.println("getAckedMessageId");
        String msg = "<RefToMessageId>xxx</RefToMessageId>";
        String expResult = "xxx";
        String result = EbXmlAcknowledgment.getAckedMessageId(msg);
        assertEquals(expResult, result);
    }

    /**
     * Test of setResponse method, of class EbXmlAcknowledgment.
     */
    @Test
    public void testSetResponse() {
        System.out.println("setResponse");
        // coded as empty block
        Sendable r = null;
        instance.setResponse(r);
    }

    /**
     * Test of getResponse method, of class EbXmlAcknowledgment.
     */
    @Test
    public void testGetResponse() {
        System.out.println("getResponse");
        // coded as null
        Sendable expResult = null;
        Sendable result = instance.getResponse();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageId method, of class EbXmlAcknowledgment.
     */
    @Test
    public void testGetMessageId() {
        System.out.println("getMessageId");
        // coded as null
        String expResult = null;
        String result = instance.getMessageId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessageId method, of class EbXmlAcknowledgment.
     */
    @Test
    public void testSetMessageId() {
        System.out.println("setMessageId");
        // coded as empty block
        String s = "";
        instance.setMessageId(s);
    }

    /**
     * Test of getResolvedUrl method, of class EbXmlAcknowledgment.
     */
    @Test
    public void testGetResolvedUrl() {
        System.out.println("getResolvedUrl");
        String expResult = "http://localhost:4848/reliablemessaging/intermediary";
        String result = instance.getResolvedUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHl7Payload method, of class EbXmlAcknowledgment.
     */
    @Test
    public void testGetHl7Payload() {
        System.out.println("getHl7Payload");
        // coded as null
        String expResult = null;
        String result = instance.getHl7Payload();
        assertEquals(expResult, result);
    }

}
