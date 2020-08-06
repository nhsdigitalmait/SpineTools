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

/**
 *
 * @author simonfarrow
 */
public class GeneralAttachmentTest {

    private GeneralAttachment instance;

    public GeneralAttachmentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        instance = new GeneralAttachment("content-type: text/xml\r\n\r\n<a/>");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getEbxmlReference method, of class GeneralAttachment.
     */
    @Test
    public void testGetEbxmlReference() {
        System.out.println("getEbxmlReference");
        String expResult = "<eb:Reference";
        String result = instance.getEbxmlReference();
        assertTrue(result.startsWith(expResult));
    }

    /**
     * Test of setBody method, of class GeneralAttachment.
     */
    @Test
    public void testSetBody_String() {
        System.out.println("setBody");
        String s = "<a/>";
        instance.setBody(s);
    }

    /**
     * Test of setBody method, of class GeneralAttachment.
     */
    @Test
    public void testSetBody_byteArr() {
        System.out.println("setBody");
        byte[] b = new byte[]{1, 2, 3};
        instance.setBody(b);
    }

    /**
     * Test of serialise method, of class GeneralAttachment.
     */
    @Test
    public void testSerialise() {
        System.out.println("serialise");
        String expResult = "<a/>";
        String result = instance.serialise();
        assertEquals(expResult, result);
    }

}
