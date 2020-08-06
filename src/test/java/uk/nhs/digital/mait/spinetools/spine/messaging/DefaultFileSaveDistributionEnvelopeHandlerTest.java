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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import uk.nhs.digital.mait.distributionenvelopetools.itk.distributionenvelope.DistributionEnvelope;
import uk.nhs.digital.mait.distributionenvelopetools.itk.distributionenvelope.DistributionEnvelopeHelper;

/**
 *
 * @author simonfarrow
 */
public class DefaultFileSaveDistributionEnvelopeHandlerTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private DefaultFileSaveDistributionEnvelopeHandler instance;

    public DefaultFileSaveDistributionEnvelopeHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("org.warlock.spine.messaging.defaultdistributionenvelopehandler.filesavedirectory", "test");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        instance = new DefaultFileSaveDistributionEnvelopeHandler();
    }

    @After
    public void tearDown() {
        for ( File file : new File("test").listFiles()) {
            if ( file.getName().endsWith(".message")) {
                System.out.println(file.getName());
                file.delete();
            }
        }
    }

    /**
     * Test of handle method, of class
     * DefaultFileSaveDistributionEnvelopeHandler.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testHandle() throws Exception {
        System.out.println("handle");
        InputStream fis = new FileInputStream(System.getenv("TKWROOT") + "/contrib/ITK_2_01_Test_Messages/Correspondence_DE/Ambulance/POCD_MT030001UK01_DIST_Primary.xml");
        DistributionEnvelope d = DistributionEnvelopeHelper.getInstance().getDistributionEnvelope(fis);
        instance.handle(d);
        // TODO
        // creates a .message file in test containg the DE
    }

    /**
     * Test of getFileSafeMessageID method, of class
     * DefaultFileSaveDistributionEnvelopeHandler.
     *
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testGetFileSafeMessageID() throws FileNotFoundException {
        System.out.println("getFileSafeMessageID");
        String s = "a:b:c";
        String expResult = "a_b_c";
        String result = instance.getFileSafeMessageID(s);
        assertEquals(expResult, result);
    }

}
