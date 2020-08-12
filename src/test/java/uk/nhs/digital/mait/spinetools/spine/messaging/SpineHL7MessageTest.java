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
public class SpineHL7MessageTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private SpineHL7Message instance;

    public SpineHL7MessageTest() {
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
        SdsTransmissionDetails c = ConnectionManager.getInstance().getTransmissionDetails("urn:nhs:names:services:itk:COPC_IN000001GB01", "YEA", null, null).get(0);
        instance = new SpineHL7Message("id", "");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getInteractionId method, of class SpineHL7Message.
     */
    @Test
    public void testGetInteractionId() {
        System.out.println("getInteractionId");
        String expResult = "id";
        String result = instance.getInteractionId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageID method, of class SpineHL7Message.
     */
    @Test
    public void testGetMessageID() {
        System.out.println("getMessageID");
        int expResult = 36;
        String result = instance.getMessageID();
        assertEquals(expResult, result.length());
    }

    /**
     * Test of isQuery method, of class SpineHL7Message.
     */
    @Test
    public void testIsQuery() {
        System.out.println("isQuery");
        boolean expResult = false;
        boolean result = instance.isQuery();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMyAsid method, of class SpineHL7Message.
     */
    @Test
    public void testGetMyAsid() {
        System.out.println("getMyAsid");
        String expResult = null;
        String result = instance.getMyAsid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMyAsid method, of class SpineHL7Message.
     */
    @Test
    public void testSetMyAsid() {
        System.out.println("setMyAsid");
        String value = "";
        instance.setMyAsid(value);
    }

    /**
     * Test of getFromAsid method, of class SpineHL7Message.
     */
    @Test
    public void testGetFromAsid() {
        System.out.println("getFromAsid");
        String expResult = null;
        String result = instance.getFromAsid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getToAsid method, of class SpineHL7Message.
     */
    @Test
    public void testGetToAsid() {
        System.out.println("getToAsid");
        String expResult = null;
        String result = instance.getToAsid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setToAsid method, of class SpineHL7Message.
     */
    @Test
    public void testSetToAsid() {
        System.out.println("setToAsid");
        String value = "";
        instance.setToAsid(value);
    }

    /**
     * Test of getAuthorUid method, of class SpineHL7Message.
     */
    @Test
    public void testGetAuthorUid() {
        System.out.println("getAuthorUid");
        String expResult = null;
        String result = instance.getAuthorUid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAuthorUid method, of class SpineHL7Message.
     */
    @Test
    public void testSetAuthorUid() {
        System.out.println("setAuthorUid");
        String value = "";
        instance.setAuthorUid(value);
    }

    /**
     * Test of getAuthorUrp method, of class SpineHL7Message.
     */
    @Test
    public void testGetAuthorUrp() {
        System.out.println("getAuthorUrp");
        String expResult = null;
        String result = instance.getAuthorUrp();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAuthorUrp method, of class SpineHL7Message.
     */
    @Test
    public void testSetAuthorUrp() {
        System.out.println("setAuthorUrp");
        String value = "";
        instance.setAuthorUrp(value);
    }

    /**
     * Test of getAuthorRole method, of class SpineHL7Message.
     */
    @Test
    public void testGetAuthorRole() {
        System.out.println("getAuthorRole");
        String expResult = null;
        String result = instance.getAuthorRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAuthorRole method, of class SpineHL7Message.
     */
    @Test
    public void testSetAuthorRole() {
        System.out.println("setAuthorRole");
        String value = "";
        instance.setAuthorRole(value);
    }

    /**
     * Test of getEbxmlReference method, of class SpineHL7Message.
     */
    @Test
    public void testGetEbxmlReference() {
        System.out.println("getEbxmlReference");
        String expResult = "<eb:Reference";
        String result = instance.getEbxmlReference();
        assertTrue(result.startsWith(expResult));
    }

    /**
     * Test of serialise method, of class SpineHL7Message.
     */
    @Test
    public void testSerialise() {
        System.out.println("serialise");
        String expResult = "<?xml version";
        String result = instance.serialise();
        assertTrue(result.startsWith(expResult));
    }

    /**
     * Test of getHL7Payload method, of class SpineHL7Message.
     */
    @Test
    public void testGetHL7Payload() {
        System.out.println("getHL7Payload");
        String expResult = "";
        String result = instance.getHL7Payload();
        assertEquals(expResult, result);
    }

}
