/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.nhs.digital.mait.spinetools.spine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author simonfarrow
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({uk.nhs.digital.mait.spinetools.spine.messaging.MessagingSuite.class, uk.nhs.digital.mait.spinetools.spine.commandline.CommandlineSuite.class, uk.nhs.digital.mait.spinetools.spine.connection.ConnectionSuite.class, uk.nhs.digital.mait.spinetools.spine.logging.LoggingSuite.class})
public class SpineSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
