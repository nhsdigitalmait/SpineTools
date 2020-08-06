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
import uk.nhs.digital.mait.distributionenvelopetools.itk.distributionenvelope.DistributionEnvelope;

/**
 *
 * @author simonfarrow
 */
public class DistributionEnvelopeHandlerTest {
    
    public DistributionEnvelopeHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of handle method, of class DistributionEnvelopeHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testHandle() throws Exception {
        System.out.println("handle");
        DistributionEnvelope d = null;
        DistributionEnvelopeHandler instance = new DistributionEnvelopeHandlerImpl();
        instance.handle(d);
    }

    public class DistributionEnvelopeHandlerImpl implements DistributionEnvelopeHandler {

        @Override
        public void handle(DistributionEnvelope d) throws Exception {
            System.out.println("handle called");
        }
    }
    
}
