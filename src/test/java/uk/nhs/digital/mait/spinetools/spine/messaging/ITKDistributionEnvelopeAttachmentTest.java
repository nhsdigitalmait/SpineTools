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

import java.io.FileInputStream;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.nhs.digital.mait.distributionenvelopetools.itk.distributionenvelope.DistributionEnvelope;
import uk.nhs.digital.mait.distributionenvelopetools.itk.distributionenvelope.DistributionEnvelopeHelper;

/**
 *
 * @author simonfarrow
 */
public class ITKDistributionEnvelopeAttachmentTest {

    private ITKDistributionEnvelopeAttachment instance;
    
    public ITKDistributionEnvelopeAttachmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        InputStream is = new FileInputStream(System.getenv("TKWROOT") + "/contrib/ITK_2_01_Test_Messages/Correspondence_DE/Ambulance/POCD_MT030001UK01_DIST_Primary.xml");
        DistributionEnvelope de = DistributionEnvelopeHelper.getInstance().getDistributionEnvelope(is);
        instance = new ITKDistributionEnvelopeAttachment(de);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEbxmlReference method, of class ITKDistributionEnvelopeAttachment.
     */
    @Test
    public void testGetEbxmlReference() {
        System.out.println("getEbxmlReference");
        String expResult = "<eb:Reference";
        String result = instance.getEbxmlReference();
        assertTrue(result.startsWith(expResult));
    }

    /**
     * Test of getDistributionEnvelope method, of class ITKDistributionEnvelopeAttachment.
     */
    @Test
    public void testGetDistributionEnvelope() {
        System.out.println("getDistributionEnvelope");
        DistributionEnvelope result = instance.getDistributionEnvelope();
        assertNotNull(result);
    }

    /**
     * Test of serialise method, of class ITKDistributionEnvelopeAttachment.
     */
    @Test
    public void testSerialise() {
        System.out.println("serialise");
        String expResult = "\n<itk:DistributionEnvelope";
        String result = instance.serialise();
        assertTrue(result.startsWith(expResult));
    }
    
}
