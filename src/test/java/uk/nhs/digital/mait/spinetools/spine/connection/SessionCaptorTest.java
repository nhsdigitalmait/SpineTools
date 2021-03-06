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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.nhs.digital.mait.spinetools.spine.messaging.Sendable;

/**
 *
 * @author simonfarrow
 */
public class SessionCaptorTest {

    public SessionCaptorTest() {
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
     * Test of capture method, of class SessionCaptor.
     */
    @Test
    public void testCapture() {
        System.out.println("capture");
        Sendable s = null;
        SessionCaptor instance = new SessionCaptorImpl();
        instance.capture(s);
    }

    public class SessionCaptorImpl implements SessionCaptor {

        public void capture(Sendable s) {
        }
    }

}
