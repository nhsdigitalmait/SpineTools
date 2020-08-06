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

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SDSSpineEndpointResolverTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private SDSSpineEndpointResolver instance;

    public SDSSpineEndpointResolverTest() {
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
        try {
            instance = new SDSSpineEndpointResolver();
        } catch (Exception ex) {
            Logger.getLogger(SDSSpineEndpointResolverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMyAsid method, of class SDSSpineEndpointResolver.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMyAsid() throws Exception {
        System.out.println("getMyAsid");
        String expResult = "SIAB-001";
        String result = instance.getMyAsid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMyPartyKey method, of class SDSSpineEndpointResolver.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMyPartyKey() throws Exception {
        System.out.println("getMyPartyKey");
        String expResult = "YEA-801248";
        String result = instance.getMyPartyKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of resolveUrl method, of class SDSSpineEndpointResolver.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testResolveUrl() throws Exception {
        System.out.println("resolveUrl");
        String svcia = "urn:nhs:names:services:itk:COPC_IN000001GB01";
        String expResult = "http://localhost:4848/reliablemessaging/intermediary";
        String result = instance.resolveUrl(svcia);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTransmissionDetails method, of class SDSSpineEndpointResolver.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTransmissionDetails() throws Exception {
        System.out.println("getTransmissionDetails");
        String s = "urn:nhs:names:services:itk:COPC_IN000001GB01";
        String o = "YEA";
        String a = null;
        String p = null;
        int expResult = 1;
        ArrayList<SdsTransmissionDetails> result = instance.getTransmissionDetails(s, o, a, p);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of substitute method, of class SDSSpineEndpointResolver.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSubstitute() throws Exception {
        System.out.println("substitute");
        StringBuilder sb = new StringBuilder();
        String tag = "__TAG__";
        String content = "aa __TAG__ cc";
        instance.substitute(sb, tag, content);
    }

}
