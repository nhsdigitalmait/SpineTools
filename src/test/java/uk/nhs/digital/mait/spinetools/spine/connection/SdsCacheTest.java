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
public class SdsCacheTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private SdsCache instance;

    private static final String ORG_ID = "YEA";
    private static final String SVCIA = "urn:nhs:names:services:itk:COPC_IN000001GB01";

    public SdsCacheTest() {
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
        instance = new SdsCache(System.getProperty("org.warlock.spine.sds.cachedir"), 1000);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of cacheTransmissionDetail method, of class SdsCache.
     */
    @Test
    public void testCacheTransmissionDetail() {
        System.out.println("cacheTransmissionDetail");
        SdsTransmissionDetails sds = instance.getSdsTransmissionDetails(SVCIA, ORG_ID, null, null).get(0);
        instance.cacheTransmissionDetail(sds);
    }

    /**
     * Test of getSdsTransmissionDetails method, of class SdsCache.
     */
    @Test
    public void testGetSdsTransmissionDetails() {
        System.out.println("getSdsTransmissionDetails");
        String svcint = SVCIA;
        String ods = ORG_ID;
        String asid = null;
        String pk = null;
        int expResult = 1;
        ArrayList<SdsTransmissionDetails> result = instance.getSdsTransmissionDetails(svcint, ods, asid, pk);
        assertEquals(expResult, result.size());
    }

}
