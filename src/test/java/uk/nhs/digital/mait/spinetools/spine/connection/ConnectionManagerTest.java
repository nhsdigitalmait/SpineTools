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

import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import uk.nhs.digital.mait.spinetools.spine.messaging.EbXmlMessage;
import uk.nhs.digital.mait.spinetools.spine.messaging.ExpiredMessageHandler;
import uk.nhs.digital.mait.spinetools.spine.messaging.Sendable;
import uk.nhs.digital.mait.spinetools.spine.messaging.SpineEbXmlHandler;
import uk.nhs.digital.mait.spinetools.spine.messaging.SpineHL7Message;
import uk.nhs.digital.mait.spinetools.spine.messaging.SpineSOAPRequest;
import uk.nhs.digital.mait.spinetools.spine.messaging.SynchronousResponseHandler;

/**
 *
 * @author simonfarrow
 */
public class ConnectionManagerTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private ExpiredMessageHandler expiredMessageHandler;
    private EbXmlEventListener ebXmlEventListener;

    private static ConnectionManager instance;
    private static File expiredDirectory;

    private static final String TEST_EXPIRED_DIRECTORY = "src/test/resources/expired_directory";
    private static final String TEST_MESSAGE_DIRECTORY = "src/test/resources/message_directory";

    private static final String MY_IP_ADDRESS = "127.0.0.1";
    private static final String MY_PARTY_KEY = "YEA-801248";
    private static final String MY_ASID = "SIAB-001";
    private static final String SVCID = "urn:nhs:names:services:itk:COPC_IN000001GB01";
    private static final String ORG_ID = "YEA";

    public ConnectionManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("org.warlock.spine.sds.cachedir", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/transmitter_source/cache");
        System.setProperty("org.warlock.spine.sds.myasid", MY_ASID);
        System.setProperty("org.warlock.spine.sds.mypartykey", MY_PARTY_KEY);

        System.setProperty("org.warlock.http.spine.certs", System.getenv("TKWROOT") + "/contrib/Test_Certificates/TLS_Test_Certificates/Test01/test01.jks");
        System.setProperty("org.warlock.http.spine.sslcontextpass", "test01tls_moscow");

        System.setProperty("org.warlock.spine.messaging.expireddirectory", TEST_EXPIRED_DIRECTORY);
        System.setProperty("org.warlock.spine.messaging.messagedirectory", TEST_MESSAGE_DIRECTORY);
        System.setProperty("org.warlock.spine.connection.myip", MY_IP_ADDRESS);

        System.setProperty("org.warlock.spine.sds.url", "http://localhost");
        System.setProperty("org.warlock.spine.sds.urlresolver", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/urlresolver.txt");

        System.setProperty("org.warlock.spine.messaging.sessioncaptureclass", "uk.nhs.digital.mait.spinetools.spine.connection.BasicSessionCaptor");
        
        
        expiredDirectory = new File(TEST_EXPIRED_DIRECTORY);
        expiredDirectory.mkdirs();

        instance = ConnectionManager.getInstance();
    }

    @AfterClass
    public static void tearDownClass() {
        for (File log : expiredDirectory.listFiles()) {
            log.delete();
        }
        expiredDirectory.delete();
    }

    @Before
    public void setUp() {
        expiredMessageHandler = (Sendable s) -> {
            System.out.println("Expired message handler called for message " + s.getMessageId());
        };
        ebXmlEventListener = (String msgId, String comment1, String ack1) -> {
            System.out.println("Event received for message " + msgId + "\r\nwith comment " + comment1 + "\r\nwith ack " + ack1);
        };
    }

    @After
    public void tearDown() {
       instance.stopListening();
    }

    private Sendable sendAMessage() throws Exception {
        SdsTransmissionDetails c = instance.getTransmissionDetails(SVCID, ORG_ID, null, null).get(0);
        String hl7payload = "hl7payload";
        SpineHL7Message m = new SpineHL7Message("svcid", hl7payload);
        Sendable s = new SpineSOAPRequest(c, m);
        instance.send(s, c);
        return s;
    }

    /**
     * Test of setEbXmlEventListener method, of class ConnectionManager.
     */
    @Test
    public void testSetEbXmlEventListener() {
        System.out.println("setEbXmlEventListener");
        instance.setEbXmlEventListener(ebXmlEventListener);
    }

    /**
     * Test of getSessionCaptor method, of class ConnectionManager.
     */
    @Test
    public void testGetSessionCaptor() {
        System.out.println("getSessionCaptor");
        Class expResult = uk.nhs.digital.mait.spinetools.spine.connection.BasicSessionCaptor.class;
        SessionCaptor result = instance.getSessionCaptor();
        assertEquals(expResult, result.getClass());
    }

    /**
     * Test of getMessageDirectory method, of class ConnectionManager.
     */
    @Test
    public void testGetMessageDirectory() {
        System.out.println("getMessageDirectory");
        String expResult = TEST_MESSAGE_DIRECTORY;
        String result = instance.getMessageDirectory();
        assertEquals(expResult, result);
    }

    /**
     * Test of loadPersistedMessages method, of class ConnectionManager.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadPersistedMessages() throws Exception {
        System.out.println("loadPersistedMessages");
        // conditional compilation controls currently set to mean that this method returns immediately
        instance.loadPersistedMessages();
    }

    /**
     * Test of stopRetryProcessor method, of class ConnectionManager.
     */
    @Test
    public void testStopRetryProcessor() throws Exception {
        System.out.println("stopRetryProcessor");
        
        sendAMessage(); // starts the timer so we can kill it
        instance.stopRetryProcessor();
    }

    /**
     * Test of getPasswordProvider method, of class ConnectionManager.
     */
    @Test
    public void testGetPasswordProvider() {
        System.out.println("getPasswordProvider");
        PasswordProvider result = instance.getPasswordProvider();
        assertNotNull(result);
    }

    /**
     * Test of getMyIP method, of class ConnectionManager.
     */
    @Test
    public void testGetMyIP() {
        System.out.println("getMyIP");
        String expResult = MY_IP_ADDRESS;
        String result = instance.getMyIP();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMyAsid method, of class ConnectionManager.
     */
    @Test
    public void testGetMyAsid() {
        System.out.println("getMyAsid");
        String expResult = MY_ASID;
        String result = instance.getMyAsid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMyPartyKey method, of class ConnectionManager.
     */
    @Test
    public void testGetMyPartyKey() {
        System.out.println("getMyPartyKey");
        String expResult = MY_PARTY_KEY;
        String result = instance.getMyPartyKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExpiredMessageDirectory method, of class ConnectionManager.
     */
    @Test
    public void testGetExpiredMessageDirectory() {
        System.out.println("getExpiredMessageDirectory");
        String expResult = TEST_EXPIRED_DIRECTORY;
        String result = instance.getExpiredMessageDirectory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSecurityContext method, of class ConnectionManager.
     */
    @Test
    public void testGetSecurityContext() {
        System.out.println("getSecurityContext");
        SpineSecurityContext result = instance.getSecurityContext();
        assertNotNull(result);
    }

    /**
     * Test of getTransmissionDetails method, of class ConnectionManager.
     */
    @Test
    public void testGetTransmissionDetails() {
        System.out.println("getTransmissionDetails");
        String s = SVCID; // svcia
        String o = ORG_ID; // ods code
        String a = null; // optional asid
        String p = null; // optional party key
        int expResult = 1;
        ArrayList<SdsTransmissionDetails> result = instance.getTransmissionDetails(s, o, a, p);

        assertEquals(expResult, result.size());
        assertEquals(s, result.get(0).getSvcIA());
        assertEquals(o, result.get(0).getOrgCode());

        String expStrResult = "http://localhost:xxxx/reliablemessaging/intermediary";
        assertEquals(expStrResult, result.get(0).getUrl());
    }

    /**
     * Test of getBootException method, of class ConnectionManager.
     */
    @Test
    public void testGetBootException() {
        System.out.println("getBootException");
        Exception expResult = null;
        Exception result = instance.getBootException();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstance method, of class ConnectionManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ConnectionManager result = ConnectionManager.getInstance();
        assertNotNull(result);
        assertEquals(instance,result);
    }

    /**
     * Test of listen method, of class ConnectionManager.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testListen_int() throws Exception {
        System.out.println("listen");
        int p = 4000;
        instance.listen(p);
    }

    /**
     * Test of listen method, of class ConnectionManager.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testListen_0args() throws Exception {
        System.out.println("listen");
        instance.listen();
    }

    /**
     * Test of stopListening method, of class ConnectionManager.
     */
    @Test
    public void testStopListening() {
        System.out.println("stopListening");
        instance.stopListening();
    }

    /**
     * Test of send method, of class ConnectionManager.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSend() throws Exception {
        System.out.println("send");
        Sendable s = sendAMessage();
    }

    /**
     * Test of processRetries method, of class ConnectionManager.
     */
    @Test
    public void testProcessRetries() throws Exception {
        System.out.println("processRetries");
        // has null pointer issues if run standalone without the listen
        instance.listen();
        instance.processRetries();
    }

    /**
     * Test of getPersistDuration method, of class ConnectionManager.
     */
    @Test
    public void testGetPersistDuration() {
        System.out.println("getPersistDuration");
        String svcia = "urn:nhs:names:services:op:POCD_IN170001UK07";
        int expResult = 3600;
        int result = instance.getPersistDuration(svcia);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSdsConnection method, of class ConnectionManager.
     */
    @Test
    public void testGetSdsConnection() {
        System.out.println("getSdsConnection");
        SDSconnection expResult = null;
        SDSconnection result = instance.getSdsConnection();
        assertEquals(expResult, result);
    }

    /**
     * Test of resolveUrl method, of class ConnectionManager.
     */
    @Test
    public void testResolveUrl() {
        System.out.println("resolveUrl");
        String svcia = SVCID;
        String expResult = "http://localhost:4848/reliablemessaging/intermediary";
        String result = instance.resolveUrl(svcia);
        assertEquals(expResult, result);
    }

    /**
     * Test of addSynchronousResponseHandler method, of class ConnectionManager.
     */
    @Test
    public void testAddSynchronousResponseHandler() {
        System.out.println("addSynchronousResponseHandler");
        String sa = "testhandler";
        SynchronousResponseHandler h = (SpineSOAPRequest r) -> {
        };
        instance.addSynchronousResponseHandler(sa, h);
    }

    /**
     * Test of getSynchronousResponseHandler method, of class ConnectionManager.
     */
    @Test
    public void testGetSynchronousResponseHandler() {
        System.out.println("getSynchronousResponseHandler");
        String sa = "testhandler";
        SynchronousResponseHandler expResult = (SpineSOAPRequest r) -> {
        };
        instance.addSynchronousResponseHandler(sa, expResult);
        SynchronousResponseHandler result = instance.getSynchronousResponseHandler(sa);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEbXmlHandler method, of class ConnectionManager.
     */
    @Test
    public void testGetEbXmlHandler() {
        System.out.println("getEbXmlHandler");
        String sa = "testhandler";
        SpineEbXmlHandler expResult = (EbXmlMessage m) -> {
        };
        instance.addHandler(sa, expResult);
        SpineEbXmlHandler result = instance.getEbXmlHandler(sa);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerAck method, of class ConnectionManager.
     */
    @Test
    public void testRegisterAck() throws Exception {
        System.out.println("registerAck");

        Sendable s = sendAMessage();

        String a = s.getMessageId();
        String comment = "comment";
        String ack = "ack";
        instance.setEbXmlEventListener(ebXmlEventListener);

        // removes the message that's just been added but doesn't delete the file because there isn't one yet
        instance.registerAck(a, comment, ack);
    }

    /**
     * Test of removeRequest method, of class ConnectionManager.
     */
    @Test
    public void testRemoveRequest() throws Exception {
        System.out.println("removeRequest");
        Sendable s = sendAMessage();

        String a = s.getMessageId();
        String comment = "";
        instance.removeRequest(a, comment);
    }

    /**
     * Test of addHandler method, of class ConnectionManager.
     */
    @Test
    public void testAddHandler() {
        System.out.println("addHandler");
        String sa = "testhandler";
        SpineEbXmlHandler h = (EbXmlMessage m) -> {
        };
        instance.addHandler(sa, h);
    }

    /**
     * Test of addExpiryHandler method, of class ConnectionManager.
     */
    @Test
    public void testAddExpiryHandler() {
        System.out.println("addExpiryHandler");
        String sa = "testhandler";
        instance.addExpiryHandler(sa, expiredMessageHandler);
    }

    /**
     * Test of getExpiryHandler method, of class ConnectionManager.
     */
    @Test
    public void testGetExpiryHandler() {
        System.out.println("getExpiryHandler");
        String sa = "testhandler";
        instance.addExpiryHandler(sa, expiredMessageHandler);
        ExpiredMessageHandler expResult = expiredMessageHandler;
        ExpiredMessageHandler result = instance.getExpiryHandler(sa);
        assertEquals(expResult, result);
    }

    /**
     * Test of substitute method, of class ConnectionManager.
     */
    @Test
    public void testSubstitute() {
        System.out.println("substitute");
        StringBuilder sb = new StringBuilder();
        String tag = "__TAG__";
        String content = "aa __TAG__ cc";
        boolean expResult = false;
        boolean result = ConnectionManager.substitute(sb, tag, content);
        assertEquals(expResult, result);
    }

}
