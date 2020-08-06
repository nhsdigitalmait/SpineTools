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
package uk.nhs.digital.mait.spinetools.spine.connection;

import java.util.ArrayList;
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
public class SdsTransmissionDetailsTest {

    private SdsTransmissionDetails instance;
    
    public SdsTransmissionDetailsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new SdsTransmissionDetails();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setOrgCode method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetOrgCode() {
        System.out.println("setOrgCode");
        String s = "";
        instance.setOrgCode(s);
    }

    /**
     * Test of setPartyKey method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetPartyKey() {
        System.out.println("setPartyKey");
        String s = "";
        instance.setPartyKey(s);
    }

    /**
     * Test of setCPAid method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetCPAid() {
        System.out.println("setCPAid");
        String s = "";
        instance.setCPAid(s);
    }

    /**
     * Test of setInteractionId method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetInteractionId() {
        System.out.println("setInteractionId");
        String s = "";
        instance.setInteractionId(s);
    }

    /**
     * Test of setSvcIA method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetSvcIA() {
        System.out.println("setSvcIA");
        String s = "";
        instance.setSvcIA(s);
    }

    /**
     * Test of setService method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetService() {
        System.out.println("setService");
        String s = "";
        instance.setService(s);
    }

    /**
     * Test of setSyncReply method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetSyncReply() {
        System.out.println("setSyncReply");
        String s = "";
        instance.setSyncReply(s);
    }

    /**
     * Test of setSoapActor method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetSoapActor() {
        System.out.println("setSoapActor");
        String s = "";
        instance.setSoapActor(s);
    }

    /**
     * Test of setAckRequested method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetAckRequested() {
        System.out.println("setAckRequested");
        String s = "";
        instance.setAckRequested(s);
    }

    /**
     * Test of setDuplicateElimination method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetDuplicateElimination() {
        System.out.println("setDuplicateElimination");
        String s = "";
        instance.setDuplicateElimination(s);
    }

    /**
     * Test of setUrl method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetUrl() {
        System.out.println("setUrl");
        String s = "";
        instance.setUrl(s);
    }

    /**
     * Test of addAsid method, of class SdsTransmissionDetails.
     */
    @Test
    public void testAddAsid() {
        System.out.println("addAsid");
        String s = "";
        instance.addAsid(s);
    }

    /**
     * Test of setRetryInterval method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetRetryInterval() {
        System.out.println("setRetryInterval");
        int i = 0;
        instance.setRetryInterval(i);
    }

    /**
     * Test of setRetries method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetRetries() {
        System.out.println("setRetries");
        int i = 0;
        instance.setRetries(i);
    }

    /**
     * Test of setPersistDuration method, of class SdsTransmissionDetails.
     */
    @Test
    public void testSetPersistDuration() {
        System.out.println("setPersistDuration");
        int i = 0;
        instance.setPersistDuration(i);
    }

    /**
     * Test of getUrl method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        String expResult = null;
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrgCode method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetOrgCode() {
        System.out.println("getOrgCode");
        String expResult = null;
        String result = instance.getOrgCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPartyKey method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetPartyKey() {
        System.out.println("getPartyKey");
        String expResult = null;
        String result = instance.getPartyKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCPAid method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetCPAid() {
        System.out.println("getCPAid");
        String expResult = null;
        String result = instance.getCPAid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInteractionId method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetInteractionId() {
        System.out.println("getInteractionId");
        String expResult = null;
        String result = instance.getInteractionId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSvcIA method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetSvcIA() {
        System.out.println("getSvcIA");
        String expResult = null;
        String result = instance.getSvcIA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getService method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetService() {
        System.out.println("getService");
        String expResult = null;
        String result = instance.getService();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSyncReply method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetSyncReply() {
        System.out.println("getSyncReply");
        String expResult = null;
        String result = instance.getSyncReply();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSoapActor method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetSoapActor() {
        System.out.println("getSoapActor");
        String expResult = null;
        String result = instance.getSoapActor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAckRequested method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetAckRequested() {
        System.out.println("getAckRequested");
        String expResult = null;
        String result = instance.getAckRequested();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDuplicateElimination method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetDuplicateElimination() {
        System.out.println("getDuplicateElimination");
        String expResult = "";
        String result = instance.getDuplicateElimination();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAsids method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetAsids() {
        System.out.println("getAsids");
        int expResult = 0;
        ArrayList<String> result = instance.getAsids();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getRetryInterval method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetRetryInterval() {
        System.out.println("getRetryInterval");
        int expResult = -1;
        int result = instance.getRetryInterval();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRetries method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetRetries() {
        System.out.println("getRetries");
        int expResult = -1;
        int result = instance.getRetries();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPersistDuration method, of class SdsTransmissionDetails.
     */
    @Test
    public void testGetPersistDuration() {
        System.out.println("getPersistDuration");
        int expResult = -1;
        int result = instance.getPersistDuration();
        assertEquals(expResult, result);
    }

    /**
     * Test of isSynchronous method, of class SdsTransmissionDetails.
     */
    @Test
    public void testIsSynchronous() {
        System.out.println("isSynchronous");
        boolean expResult = true;
        boolean result = instance.isSynchronous();
        assertEquals(expResult, result);
    }

    /**
     * Test of iso8601DurationToSeconds method, of class SdsTransmissionDetails.
     */
    @Test
    public void testIso8601DurationToSeconds() {
        System.out.println("iso8601DurationToSeconds");
        String d = "";
        int expResult = 0;
        int result = SdsTransmissionDetails.iso8601DurationToSeconds(d);
        assertEquals(expResult, result);
    }
    
}
