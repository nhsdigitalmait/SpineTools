/*

Copyright 2014 Health and Social Care Information Centre
 Solution Assurance damian.murphy@hscic.gov.uk

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

/**
 * Conditional compilation controller. This class exists to support static final
 * boolean values that control "conditional compilation".<BR><BR>
 * 
 * "TESTHARNESS" that controls whether clear-text sockets are available to the SpineTools
 * packages. Production use of the SpineTools should not need to set the "TESTHARNESS"
 * constant to true - it exists principally to allow use of SpineTools in the ITK
 * Testbench system, without imposing a lot of run-time conditions for other uses.<BR><BR>
 * 
 * @author Damian Murphy damian.murphy@hscic.gov.uk
 */
public class ConditionalCompilationControls {
   
    /**
     * Set to true for environments where the SpineSecurityContext is needed to establish
     * an SSL connection to SDS before LDAP is spoken over it. False just uses the stock
     * Java LDAPS libraries.
     */
    public static final boolean LDAPOVERTLS = false;
    
    /**
     * Set to false for an environment where SDS is in clear
     */
    public static final boolean LDAPS = false;
    
    /**
     * Set to true when using the opentest environment to satisfy SDS requests.
     */
    public static final boolean OPENTEST = false;
    /**
     * Set true to enable clear-text sockets, false otherwise.
     */
    // Set true to enable clear-text sockets, false otherwise.
    public static final boolean TESTHARNESS = true;
    
/*----------DOES NOT REQUIRE TESTHARNESS MODE---------------------------------*/

    //Set if only using SDS cache
    // public static boolean sdscacheonly = true;
    
    // Do we dump received messages to stdout for analysis ?
    // Does NOT Require TESTHARNESS mode
    public static final boolean DUMP_RECEIVED_MESSAGE = true;
    
/*-----------REQUIRES TESTHARNESS MODE----------------------------------------*/
    
    // Set if clear-text sockets are requested.
    // Requires TESTHARNESS mode
    public static boolean cleartext = true;

    // On the Wire Message Logging
    // Requires TESTHARNESS mode
    public static final boolean otwMessageLogging = true;

    // Counter to determine whether a configurable non-response to sync should happen every 2 interactions
    // Requires TESTHARNESS mode
    public static int synccounter = 0;
}
