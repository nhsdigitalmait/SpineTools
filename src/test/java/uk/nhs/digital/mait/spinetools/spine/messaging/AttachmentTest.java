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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;

/**
 *
 * @author simonfarrow
 */
public class AttachmentTest {

    private AttachmentImpl instance;
    
    public AttachmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new AttachmentImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setMimeType method, of class Attachment.
     */
    @Test
    public void testSetMimeType() {
        System.out.println("setMimeType");
        String m = "text/xml";
        instance.setMimeType(m);
    }

    /**
     * Test of getEbxmlReference method, of class Attachment.
     */
    @Test
    public void testGetEbxmlReference() {
        System.out.println("getEbxmlReference");
        String expResult = "";
        String result = instance.getEbxmlReference();
        assertEquals(expResult, result);
    }

    /**
     * Test of serialise method, of class Attachment.
     */
    @Test
    public void testSerialise() {
        System.out.println("serialise");
        String expResult = "";
        String result = instance.serialise();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMimeType method, of class Attachment.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMimeType_String() throws Exception {
        System.out.println("getMimeType");
        String s = "aaa\r\nContent-type:  text/xml\r\nbb\r\n";

        String expResult = "text/xml";
        String result = instance.getMimeType(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of stripMimeHeaders method, of class Attachment.
     * @throws java.lang.Exception
     */
    @Test
    public void testStripMimeHeaders() throws Exception {
        System.out.println("stripMimeHeaders");
        String s = "mime\r\n\r\n<a/>";
        String expResult = "<a/>";
        String result = instance.stripMimeHeaders(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseReceivedXml method, of class Attachment.
     * @throws java.lang.Exception
     */
    @Test
    public void testParseReceivedXml() throws Exception {
        System.out.println("parseReceivedXml");
        String s="mime\r\n\r\n<a/>";
        String expResult = "a";
        Document result = instance.parseReceivedXml(s);
        assertEquals(expResult, result.getDocumentElement().getLocalName());
    }

    /**
     * Test of makeMimeHeader method, of class Attachment.
     */
    @Test
    public void testMakeMimeHeader() {
        System.out.println("makeMimeHeader");
        String expResult = "Content-Id";
        String result = instance.makeMimeHeader();
        assertTrue(result.contains(expResult));
    }

    /**
     * Test of getContentId method, of class Attachment.
     */
    @Test
    public void testGetContentId() {
        System.out.println("getContentId");
        int expResult = 36;
        String result = instance.getContentId();
        assertEquals(expResult, result.length());
    }

    /**
     * Test of getDescription method, of class Attachment.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "description";
        instance.setDescription(expResult);
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Attachment.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String value = "description";
        instance.setDescription(value);
    }

    /**
     * Test of getSchema method, of class Attachment.
     */
    @Test
    public void testGetSchema() {
        System.out.println("getSchema");
        String expResult = "schema";
        instance.schema = expResult;
        String result = instance.getSchema();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSchema method, of class Attachment.
     */
    @Test
    public void testSetSchema() {
        System.out.println("setSchema");
        String value = "schema";
        instance.setSchema(value);
    }

    /**
     * Test of getMimeType method, of class Attachment.
     */
    @Test
    public void testGetMimeType_0args() {
        System.out.println("getMimeType");
        String expResult = "text/xml";
        instance.mimetype = expResult;
        String result = instance.getMimeType();
        assertEquals(expResult, result);
    }

    /**
     * Test of substitute method, of class Attachment.
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

    public class AttachmentImpl extends Attachment {
        
        public AttachmentImpl() {
            super();
        }

        @Override
        public String getEbxmlReference() {
            return "";
        }

        @Override
        public String serialise() {
            return "";
        }
    }
    
}
