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
package uk.nhs.digital.mait.spinetools.spine.logging;

import java.util.logging.Level;
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
public class SpineToolsLoggerTest {

    private SpineToolsLogger instance;
    
    public SpineToolsLoggerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = SpineToolsLogger .getInstance();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDate method, of class SpineToolsLogger.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        int expResult = 14;
        String result = SpineToolsLogger.getDate();
        assertEquals(expResult, result.length());
    }

    /**
     * Test of log method, of class SpineToolsLogger.
     */
    @Test
    public void testLog_String_Exception() {
        System.out.println("log");
        String location = "";
        Exception e = new Exception("testexception");
        instance.log(location, e);
    }

    /**
     * Test of log method, of class SpineToolsLogger.
     */
    @Test
    public void testLog_String_String() {
        System.out.println("log");
        String location = "";
        String message = "";
        instance.log(location, message);
    }

    /**
     * Test of warn method, of class SpineToolsLogger.
     */
    @Test
    public void testWarn() {
        System.out.println("warn");
        String location = "";
        String message = "";
        instance.warn(location, message);
    }

    /**
     * Test of fine method, of class SpineToolsLogger.
     */
    @Test
    public void testFine() {
        System.out.println("fine");
        String location = "";
        String message = "";
        instance.fine(location, message);
    }

    /**
     * Test of finer method, of class SpineToolsLogger.
     */
    @Test
    public void testFiner() {
        System.out.println("finer");
        String location = "";
        String message = "";
        instance.finer(location, message);
    }

    /**
     * Test of finest method, of class SpineToolsLogger.
     */
    @Test
    public void testFinest() {
        System.out.println("finest");
        String location = "";
        String message = "";
        instance.finest(location, message);
    }

    /**
     * Test of info method, of class SpineToolsLogger.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        String location = "";
        String message = "";
        instance.info(location, message);
    }

    /**
     * Test of error method, of class SpineToolsLogger.
     */
    @Test
    public void testError() {
        System.out.println("error");
        String location = "";
        String message = "";
        instance.error(location, message);
    }

    /**
     * Test of log method, of class SpineToolsLogger.
     */
    @Test
    public void testLog_3args_1() {
        System.out.println("log");
        Level l = null;
        String location = "";
        Exception e = new Exception("testexception");
        instance.log(l, location, e);
    }

    /**
     * Test of log method, of class SpineToolsLogger.
     */
    @Test
    public void testLog_3args_2() {
        System.out.println("log");
        Level l = null;
        String location = "";
        String message = "";
        instance.log(l, location, message);
    }

    /**
     * Test of getInstance method, of class SpineToolsLogger.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        SpineToolsLogger result = SpineToolsLogger.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of close method, of class SpineToolsLogger.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        instance.close();
    }

    /**
     * Test of setAppName method, of class SpineToolsLogger.
     */
    @Test
    public void testSetAppName() {
        System.out.println("setAppName");
        String name = "";
        String ldir = "";
        instance.setAppName(name, ldir);
    }

    /**
     * Test of log method, of class SpineToolsLogger.
     */
    @Test
    public void testLog_String() {
        System.out.println("log");
        String string = "";
        instance.log(string);
    }
    
}
