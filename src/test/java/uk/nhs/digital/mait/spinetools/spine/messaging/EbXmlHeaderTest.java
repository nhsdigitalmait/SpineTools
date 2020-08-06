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

import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simonfarrow
 */
public class EbXmlHeaderTest {

    private EbXmlHeader instance;

    public EbXmlHeaderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        instance = new EbXmlHeader(new String(Files.readAllBytes(
                Paths.get(System.getenv("TKWROOT") + "/contrib/SPINE_Test_Messages/MTH_Test_Messages/PDS2008A_Example_Input_Msg/QUPA_IN000006UK02_QUPA_IN000011UK02.xml"))));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSvcIA method, of class EbXmlHeader.
     */
    @Test
    public void testGetSvcIA() {
        System.out.println("getSvcIA");
        String expResult = "urn:nhs:names:services:pdsquery:QUPA_IN000006UK02";
        String result = instance.getSvcIA();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMyPartyKey method, of class EbXmlHeader.
     */
    @Test
    public void testSetMyPartyKey() {
        System.out.println("setMyPartyKey");
        String s = "";
        instance.setMyPartyKey(s);
    }

    /**
     * Test of getEbxmlReference method, of class EbXmlHeader.
     */
    @Test
    public void testGetEbxmlReference() {
        System.out.println("getEbxmlReference");
        String expResult = "";
        String result = instance.getEbxmlReference();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSyncReply method, of class EbXmlHeader.
     */
    @Test
    public void testGetSyncReply() {
        System.out.println("getSyncReply");
        boolean expResult = true;
        boolean result = instance.getSyncReply();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimestamp method, of class EbXmlHeader.
     */
    @Test
    public void testGetTimestamp() {
        System.out.println("getTimestamp");
        String expResult = "2013-03-18T11:40:14Z";
        String result = instance.getTimestamp();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTimestamp method, of class EbXmlHeader.
     */
    @Test
    public void testSetTimestamp() {
        System.out.println("setTimestamp");
        String value = "";
        instance.setTimestamp(value);
    }

    /**
     * Test of getSoapActor method, of class EbXmlHeader.
     */
    @Test
    public void testGetSoapActor() {
        System.out.println("getSoapActor");
        String expResult = "actor";
        instance.setSoapActor(expResult);
        String result = instance.getSoapActor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSoapActor method, of class EbXmlHeader.
     */
    @Test
    public void testSetSoapActor() {
        System.out.println("setSoapActor");
        String value = "";
        instance.setSoapActor(value);
    }

    /**
     * Test of getToPartyKey method, of class EbXmlHeader.
     */
    @Test
    public void testGetToPartyKey() {
        System.out.println("getToPartyKey");
        String expResult = "topk";
        instance.setToPartyKey(expResult);
        String result = instance.getToPartyKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of setToPartyKey method, of class EbXmlHeader.
     */
    @Test
    public void testSetToPartyKey() {
        System.out.println("setToPartyKey");
        String value = "";
        instance.setToPartyKey(value);
    }

    /**
     * Test of getFromPartyKey method, of class EbXmlHeader.
     */
    @Test
    public void testGetFromPartyKey() {
        System.out.println("getFromPartyKey");
        String expResult = "RHM-801710";
        String result = instance.getFromPartyKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFromPartyKey method, of class EbXmlHeader.
     */
    @Test
    public void testSetFromPartyKey() {
        System.out.println("setFromPartyKey");
        String value = "";
        instance.setFromPartyKey(value);
    }

    /**
     * Test of getConversationId method, of class EbXmlHeader.
     */
    @Test
    public void testGetConversationId() {
        System.out.println("getConversationId");
        String expResult = "993C8839-8FC0-11E2-A805-AFEB563F31BA";
        String result = instance.getConversationId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConversationId method, of class EbXmlHeader.
     */
    @Test
    public void testSetConversationId() {
        System.out.println("setConversationId");
        String value = "";
        instance.setConversationId(value);
    }

    /**
     * Test of getDuplicateElimination method, of class EbXmlHeader.
     */
    @Test
    public void testGetDuplicateElimination() {
        System.out.println("getDuplicateElimination");
        boolean expResult = false;
        boolean result = instance.getDuplicateElimination();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDuplicationElimination method, of class EbXmlHeader.
     */
    @Test
    public void testSetDuplicationElimination() {
        System.out.println("setDuplicationElimination");
        boolean value = false;
        instance.setDuplicationElimination(value);
    }

    /**
     * Test of ackRequested method, of class EbXmlHeader.
     */
    @Test
    public void testAckRequested() {
        System.out.println("ackRequested");
        boolean expResult = false;
        boolean result = instance.ackRequested();
        assertEquals(expResult, result);
    }

    /**
     * Test of getService method, of class EbXmlHeader.
     */
    @Test
    public void testGetService() {
        System.out.println("getService");
        String expResult = "urn:nhs:names:services:pdsquery";
        String result = instance.getService();
        assertEquals(expResult, result);
    }

    /**
     * Test of setService method, of class EbXmlHeader.
     */
    @Test
    public void testSetService() {
        System.out.println("setService");
        String value = "";
        instance.setService(value);
    }

    /**
     * Test of getInteractionId method, of class EbXmlHeader.
     */
    @Test
    public void testGetInteractionId() {
        System.out.println("getInteractionId");
        String expResult = "QUPA_IN000006UK02";
        String result = instance.getInteractionId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInteractionId method, of class EbXmlHeader.
     */
    @Test
    public void testSetInteractionId() {
        System.out.println("setInteractionId");
        String value = "";
        instance.setInteractionId(value);
    }

    /**
     * Test of getMessageId method, of class EbXmlHeader.
     */
    @Test
    public void testGetMessageId() {
        System.out.println("getMessageId");
        String expResult = "993C8839-8FC0-11E2-A805-AFEB563F31BA";
        String result = instance.getMessageId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessageId method, of class EbXmlHeader.
     */
    @Test
    public void testSetMessageId() {
        System.out.println("setMessageId");
        String value = "";
        instance.setMessageId(value);
    }

    /**
     * Test of getCpaId method, of class EbXmlHeader.
     */
    @Test
    public void testGetCpaId() {
        System.out.println("getCpaId");
        String expResult = "S2001924A2012004";
        String result = instance.getCpaId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCpaId method, of class EbXmlHeader.
     */
    @Test
    public void testSetCpaId() {
        System.out.println("setCpaId");
        String value = "";
        instance.setCpaId(value);
    }

    /**
     * Test of serialise method, of class EbXmlHeader.
     */
    @Test
    public void testSerialise() {
        System.out.println("serialise");
        String expResult = "---";
        String result = instance.serialise();
        assertTrue(result.startsWith(expResult));
    }

}
