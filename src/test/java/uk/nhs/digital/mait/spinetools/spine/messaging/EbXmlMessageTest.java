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

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import uk.nhs.digital.mait.spinetools.spine.connection.SDSSpineEndpointResolver;
import uk.nhs.digital.mait.spinetools.spine.connection.SdsTransmissionDetails;

/**
 *
 * @author simonfarrow
 */
public class EbXmlMessageTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private EbXmlMessage instance;
    private SDSSpineEndpointResolver endPointResolver;

    public EbXmlMessageTest() {
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
    public void setUp() throws FileNotFoundException, Exception {
        endPointResolver = new SDSSpineEndpointResolver();
        FileInputStream fis = new FileInputStream(
                "src/test/resources/data/ITK_Trunk.message");
        instance = new EbXmlMessage(fis);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getHost method, of class EbXmlMessage.
     */
    @Test
    public void testGetHost() {
        System.out.println("getHost");

        String expResult = "localhost";
        String result = instance.getHost();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParseException method, of class EbXmlMessage.
     */
    @Test
    public void testGetParseException() {
        System.out.println("getParseException");
        Exception expResult = null;
        Exception result = instance.getParseException();
        assertEquals(expResult, result);
    }

    /**
     * Test of addAttachment method, of class EbXmlMessage.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddAttachment() throws Exception {
        System.out.println("addAttachment");
        Attachment a = new GeneralAttachment("content-type: text/xml\r\nmime\r\n\r\n<a/>");
        // this throws an exception because the ebxml message does include any attachments 
        // the two mime parts are just the standard soap part and the payload attachments will be in any subsequent mime parts
        instance.addAttachment(a);
    }

    /**
     * Test of makeEbXmlNack method, of class EbXmlMessage.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testMakeEbXmlNack() throws Exception {
        System.out.println("makeEbXmlNack");
        String ecode = "code";
        String ecodecontext = "content";
        String edesc = "description";
        String expResult = "<?xml version";
        String result = instance.makeEbXmlNack(ecode, ecodecontext, edesc);
        assertTrue(result.startsWith(expResult));
    }

    /**
     * Test of makeEbXmlAck method, of class EbXmlMessage.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testMakeEbXmlAck() throws Exception {
        System.out.println("makeEbXmlAck");
        boolean replaceCpaId = false;
        String expResult = "<?xml version";
        String result = instance.makeEbXmlAck(replaceCpaId);
        assertTrue(result.startsWith(expResult));
    }

    /**
     * Test of getHl7Payload method, of class EbXmlMessage.
     */
    @Test
    public void testGetHl7Payload() {
        System.out.println("getHl7Payload");
        String expResult = "<?xml version";
        String result = instance.getHl7Payload();
        assertTrue(result.startsWith(expResult));
    }

    /**
     * Test of getResolvedUrl method, of class EbXmlMessage.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetResolvedUrl() throws Exception {
        System.out.println("getResolvedUrl");
        String service = "urn:nhs:names:services:itk:COPC_IN000001GB01";
        String odsCode = "YEA";
        String asid = null;
        String partyKey = null;

        ArrayList<SdsTransmissionDetails> transmissionDetails = endPointResolver.getTransmissionDetails(service, odsCode, asid, partyKey);
        SpineHL7Message m = instance.getHL7Message();

        // different constructor
        instance = new EbXmlMessage(transmissionDetails.get(0), m);
        String expResult = "http://localhost:4848/reliablemessaging/intermediary";
        String result = instance.getResolvedUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of persist method, of class EbXmlMessage.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        // conditional compilation controls force an immediate return
        instance.persist();
    }

    /**
     * Test of setResponse method, of class EbXmlMessage.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetResponse() throws Exception {
        System.out.println("setResponse");
        FileInputStream is = new FileInputStream(System.getenv("TKWROOT") + "/contrib/SPINE_Test_Messages/MTH_Test_Messages/PDS2008A_Example_Input_Msg/PRPA_IN000203UK03_MCCI_IN010000UK13_1658.xml");
        Sendable r = new EbXmlMessage(is);
        instance.setResponse(r);
    }

    /**
     * Test of getResponse method, of class EbXmlMessage.
     */
    @Test
    public void testGetResponse() {
        System.out.println("getResponse");
        Sendable expResult = null;
        Sendable result = instance.getResponse();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageId method, of class EbXmlMessage.
     */
    @Test
    public void testGetMessageId() {
        System.out.println("getMessageId");
        int expResult = 36;
        String result = instance.getMessageId();
        assertEquals(expResult, result.length());
    }

    /**
     * Test of setMessageId method, of class EbXmlMessage.
     */
    @Test
    public void testSetMessageId() {
        System.out.println("setMessageId");
        String id = "id";
        instance.setMessageId(id);
    }

    /**
     * Test of write method, of class EbXmlMessage.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testWrite() throws Exception {
        System.out.println("write");
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        instance.write(s);
        System.out.println(s.toString());
    }

    /**
     * Test of getHeader method, of class EbXmlMessage.
     */
    @Test
    public void testGetHeader() {
        System.out.println("getHeader");
        EbXmlHeader result = instance.getHeader();
        assertNotNull(result);
    }

    /**
     * Test of getMimeBoundary method, of class EbXmlMessage.
     */
    @Test
    public void testGetMimeBoundary() {
        System.out.println("getMimeBoundary");
        String expResult = "---";
        String result = instance.getMimeBoundary();
        assertTrue(result.startsWith(expResult));
    }

    /**
     * Test of setMimeBoundary method, of class EbXmlMessage.
     */
    @Test
    public void testSetMimeBoundary() {
        System.out.println("setMimeBoundary");
        String value = "---";
        instance.setMimeBoundary(value);
    }

    /**
     * Test of getHL7Message method, of class EbXmlMessage.
     */
    @Test
    public void testGetHL7Message() {
        System.out.println("getHL7Message");
        SpineHL7Message result = instance.getHL7Message();
        assertNotNull(result);
    }

    /**
     * Test of setHL7Message method, of class EbXmlMessage.
     */
    @Test
    public void testSetHL7Message() {
        System.out.println("setHL7Message");
        SpineHL7Message value = instance.getHL7Message();
        instance.setHL7Message(value);
    }

    /**
     * Test of getAttachments method, of class EbXmlMessage.
     */
    @Test
    public void testGetAttachments() {
        System.out.println("getAttachments");
        String expResult = "ITK Trunk Message";
        ArrayList<Attachment> result = instance.getAttachments();
        assertTrue(result.size() == 1);
        assertEquals(expResult, result.get(0).description);
    }

}
