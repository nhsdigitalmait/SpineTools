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
package uk.nhs.digital.mait.spinetools.spine.commandline;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;

/**
 *
 * @author simonfarrow
 */
public class CommandLineTest {
    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private static final String PROPERTIES_FILE = System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/tkwClient.properties";

    public CommandLineTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("org.warlock.spine.sds.cachedir", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/transmitter_source/cache");
        System.setProperty("org.warlock.spine.sds.myasid", "SIAB-001");
        System.setProperty("org.warlock.spine.sds.mypartykey", "YEA-801248");

        System.setProperty("org.warlock.spine.sds.urlresolver", System.getenv("TKWROOT") + "/config/SPINE_ITKTrunk_Client/urlresolver.txt");
        System.setProperty("org.warlock.spine.odstarget", "YEA");
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
     * Test of main method, of class CommandLine.
     * @throws java.io.IOException
     */
    @Test
    public void testMainVersion() throws IOException {
        System.out.println("mainVersion");
        exit.expectSystemExitWithStatus(0);

        CommandLine.main(new String[]{"-version"});
    }

    /**
     * Test of main method, of class CommandLine.construct a message but don't
 send it, dump to stdout
     * @throws java.io.IOException
     */
    @Test
    public void testMainSend() throws IOException{
        System.out.println("mainSend");

        CommandLine.main(new String[]{PROPERTIES_FILE,
            "send",
            System.getenv("TKWROOT") + "/contrib/SPINE_Test_Messages/PDS/PRPA_IN000203UK03.xml",
            "urn:nhs:names:services:pdsquery",
            "nosend",
            "dumpmessage"});
    }

    /**
     * Test of main method, of class CommandLine.
     * @throws java.io.IOException
     */
    @Test
    public void testMainReceive() throws IOException {
        System.out.println("mainReceive");
        CommandLine.main(new String[]{PROPERTIES_FILE, "receive"});
    }

}
