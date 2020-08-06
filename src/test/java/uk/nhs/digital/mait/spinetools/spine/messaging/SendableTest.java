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

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
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
public class SendableTest {

    private SendableImpl instance;
    
    public SendableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new SendableImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of expire method, of class Sendable.
     */
    @Test
    public void testExpire() {
        System.out.println("expire");
        instance.expire();
    }

    /**
     * Test of getStarted method, of class Sendable.
     */
    @Test
    public void testGetStarted() {
        System.out.println("getStarted");
        Calendar result = instance.getStarted();
        assertNotNull(result);
    }

    /**
     * Test of lastTry method, of class Sendable.
     */
    @Test
    public void testLastTry() {
        System.out.println("lastTry");
        Calendar expResult = Calendar.getInstance();
        instance.lastTry = expResult;
        Calendar result = instance.lastTry();
        assertEquals(expResult, result);
    }

    /**
     * Test of recordTry method, of class Sendable.
     */
    @Test
    public void testRecordTry() {
        System.out.println("recordTry");
        boolean expResult = true;
        boolean result = instance.recordTry();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Sendable.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        int expResult = 0;
        int result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSoapAction method, of class Sendable.
     */
    @Test
    public void testGetSoapAction() {
        System.out.println("getSoapAction");
        String expResult = null;
        String result = instance.getSoapAction();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRetryCount method, of class Sendable.
     */
    @Test
    public void testGetRetryCount() {
        System.out.println("getRetryCount");
        int expResult = -1;
        int result = instance.getRetryCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRetryCount method, of class Sendable.
     */
    @Test
    public void testSetRetryCount() {
        System.out.println("setRetryCount");
        int r = 0;
        instance.setRetryCount(r);
    }

    /**
     * Test of getRetryInterval method, of class Sendable.
     */
    @Test
    public void testGetRetryInterval() {
        System.out.println("getRetryInterval");
        int expResult = -1;
        int result = instance.getRetryInterval();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRetryInterval method, of class Sendable.
     */
    @Test
    public void testSetRetryInterval() {
        System.out.println("setRetryInterval");
        int r = 0;
        instance.setRetryInterval(r);
    }

    /**
     * Test of getPersistDuration method, of class Sendable.
     */
    @Test
    public void testGetPersistDuration() {
        System.out.println("getPersistDuration");
        int expResult = -1;
        int result = instance.getPersistDuration();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPersistDuration method, of class Sendable.
     */
    @Test
    public void testSetPersistDuration() {
        System.out.println("setPersistDuration");
        int r = 0;
        instance.setPersistDuration(r);
    }

    /**
     * Test of getSynchronousResponse method, of class Sendable.
     */
    @Test
    public void testGetSynchronousResponse() {
        System.out.println("getSynchronousResponse");
        String expResult = null;
        String result = instance.getSynchronousResponse();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSynchronousResponse method, of class Sendable.
     */
    @Test
    public void testSetSynchronousResponse() {
        System.out.println("setSynchronousResponse");
        String r = "";
        instance.setSynchronousResponse(r);
    }

    /**
     * Test of write method, of class Sendable.
     */
    @Test
    public void testWrite() throws Exception {
        System.out.println("write");
        OutputStream s = null;
        instance.write(s);
    }

    /**
     * Test of setResponse method, of class Sendable.
     */
    @Test
    public void testSetResponse() {
        System.out.println("setResponse");
        Sendable r = null;
        instance.setResponse(r);
    }

    /**
     * Test of getResponse method, of class Sendable.
     */
    @Test
    public void testGetResponse() {
        System.out.println("getResponse");
        Sendable expResult = null;
        Sendable result = instance.getResponse();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageId method, of class Sendable.
     */
    @Test
    public void testGetMessageId() {
        System.out.println("getMessageId");
        String expResult = "";
        String result = instance.getMessageId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessageId method, of class Sendable.
     */
    @Test
    public void testSetMessageId() {
        System.out.println("setMessageId");
        String s = "";
        instance.setMessageId(s);
    }

    /**
     * Test of getResolvedUrl method, of class Sendable.
     */
    @Test
    public void testGetResolvedUrl() {
        System.out.println("getResolvedUrl");
        String expResult = "";
        String result = instance.getResolvedUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHl7Payload method, of class Sendable.
     */
    @Test
    public void testGetHl7Payload() {
        System.out.println("getHl7Payload");
        String expResult = "";
        String result = instance.getHl7Payload();
        assertEquals(expResult, result);
    }

    /**
     * Test of persist method, of class Sendable.
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        instance.persist();
    }

    /**
     * Test of substitute method, of class Sendable.
     */
    @Test
    public void testSubstitute() {
        System.out.println("substitute");
        StringBuilder sb = new StringBuilder();
        String tag = "__TAG__";
        String content = "aa __TAG__ cc";
        boolean expResult = false;
        boolean result = instance.substitute(sb, tag, content);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOnTheWireRequest method, of class Sendable.
     */
    @Test
    public void testGetOnTheWireRequest() {
        System.out.println("getOnTheWireRequest");
        byte[] expResult = null;
        byte[] result = instance.getOnTheWireRequest();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setOnTheWireRequest method, of class Sendable.
     */
    @Test
    public void testSetOnTheWireRequest() {
        System.out.println("setOnTheWireRequest");
        byte[] r = null;
        instance.setOnTheWireRequest(r);
    }

    /**
     * Test of getOnTheWireResponse method, of class Sendable.
     */
    @Test
    public void testGetOnTheWireResponse() {
        System.out.println("getOnTheWireResponse");
        byte[] expResult = null;
        byte[] result = instance.getOnTheWireResponse();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setOnTheWireResponse method, of class Sendable.
     */
    @Test
    public void testSetOnTheWireResponse() {
        System.out.println("setOnTheWireResponse");
        byte[] r = null;
        instance.setOnTheWireResponse(r);
    }

    public class SendableImpl extends Sendable {

        public void write(OutputStream s) throws Exception {
        }

        public void setResponse(Sendable r) {
        }

        public Sendable getResponse() {
            return null;
        }

        public String getMessageId() {
            return "";
        }

        public void setMessageId(String s) {
        }

        public String getResolvedUrl() {
            return "";
        }

        public String getHl7Payload() {
            return "";
        }

        public void persist() throws IOException {
        }
    }
    
}
