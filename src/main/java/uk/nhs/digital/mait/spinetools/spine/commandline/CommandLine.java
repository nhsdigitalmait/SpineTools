/*
 Copyright 2012 Damian Murphy murff@warlock.destinationOrg

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.destinationOrg/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

 /*
    Rev 95 Fixed a parsing defect in Attachment.getMimeType
 */
package uk.nhs.digital.mait.spinetools.spine.commandline;

import uk.nhs.digital.mait.distributionenvelopetools.itk.distributionenvelope.DistributionEnvelope;
import uk.nhs.digital.mait.distributionenvelopetools.itk.distributionenvelope.DistributionEnvelopeHelper;
import uk.nhs.digital.mait.spinetools.spine.connection.ConnectionManager;
import uk.nhs.digital.mait.spinetools.spine.connection.SDSSpineEndpointResolver;
import uk.nhs.digital.mait.spinetools.spine.connection.SdsTransmissionDetails;
import uk.nhs.digital.mait.spinetools.spine.messaging.EbXmlMessage;
import uk.nhs.digital.mait.spinetools.spine.messaging.ITKDistributionEnvelopeAttachment;
import uk.nhs.digital.mait.spinetools.spine.messaging.Sendable;
import uk.nhs.digital.mait.spinetools.spine.messaging.SpineHL7Message;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static uk.nhs.digital.mait.spinetools.spine.connection.ConditionalCompilationControls.DUMP_RECEIVED_MESSAGE;
import static uk.nhs.digital.mait.spinetools.spine.connection.ConditionalCompilationControls.LDAPOVERTLS;
import static uk.nhs.digital.mait.spinetools.spine.connection.ConditionalCompilationControls.LDAPS;
import static uk.nhs.digital.mait.spinetools.spine.connection.ConditionalCompilationControls.OPENTEST;
import static uk.nhs.digital.mait.spinetools.spine.connection.ConditionalCompilationControls.TESTHARNESS;
import static uk.nhs.digital.mait.spinetools.spine.connection.ConditionalCompilationControls.cleartext;
import static uk.nhs.digital.mait.spinetools.spine.connection.ConditionalCompilationControls.otwMessageLogging;
import uk.nhs.digital.mait.spinetools.spine.messaging.SpineSOAPRequest;

/**
 * Simple command line interface for sending and receiving Spine messages.
 *
 * @author Damian Murphy murff@warlock.org
 */
public class CommandLine {

    private static final int OP_NOTSET = -1;
    private static final int OP_SEND = 0;
    private static final int OP_RECEIVE = 1;
    private static final int OP_SENDPAYLOAD = 2;
    private static final int OP_SENDALL = 3;
    private static final int OP_XMITDE = 4;
    private static final int OP_SDS = 5;
    private static final int OP_DEMO = 6;
    private static int op = OP_NOTSET;

    private static boolean nosend = false;
    private static boolean dumpmessage = false;

    private static final int WINDOWS_DEFAULT_PORT = 443;
    private static int listenerPort = 4430;
    private static int waitPeriod = 20000;
    private static int delayPeriod = 1000;

    /**
     * @param args the command line arguments param 0 properties file name or
     * "-version" param 1 option send|receive|sendpayload|sendall|xmitde|demo
     */
    public static void main(String[] args) throws IOException {

        if (args[0].equals("-version")) {
            dumpVersionInfo();
            System.exit(0);
        }

        System.out.println(System.getProperty("java.home"));
        try {
            switch (args[1]) {
                case "send":
                    op = OP_SEND;
                    break;
                case "receive":
                    op = OP_RECEIVE;
                    break;
                case "sendpayload":
                    op = OP_SENDPAYLOAD;
                    break;
                case "sendall":
                    op = OP_SENDALL;
                    break;
                case "xmitde":
                    op = OP_XMITDE;
                    break;
                case "demo":
                    op = OP_DEMO;
                    break;
                default:
                    throw new Exception("Syntax error: mode not found: " + args[0]);
            }

            System.getProperties().load(new java.io.FileInputStream(args[0]));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // The default listener port is 4430 so that Unix systems can listen on a non-privileged
        // port, and use firewall rules to re-direct from port 443 (well-known HTTPS) to 4430.
        // Windows doesn't have the notion of a "privileged port" so should by default listen on
        // port 443. Users can override this in any case by setting the uk.nhs.digital.mait.spinetools.spine.listener.port
        // property to a value of their choice.
        //
        String osName = System.getProperty("os.name");
        if ((osName != null) && (osName.toLowerCase().contains("windows"))) {
            listenerPort = WINDOWS_DEFAULT_PORT;
        }

        for (String a : args) {
            if (a.contains("=")) {
                String[] override = a.split("=");
                switch (override[0]) {
                    case "targetASID":
                        System.setProperty("uk.nhs.digital.mait.spinetools.spine.toasid", override[1]);
                        break;
                    case "targetPartyKey":
                        System.setProperty("uk.nhs.digital.mait.spinetools.spine.topartykey", override[1]);
                        break;
                    case "odsCode":
                        System.setProperty("uk.nhs.digital.mait.spinetools.spine.odstarget", override[1]);
                        break;
                    case "authorRole":
                        System.setProperty("uk.nhs.digital.mait.spinetools.spine.authorrole", override[1]);
                        break;
                    case "authorURP":
                        System.setProperty("uk.nhs.digital.mait.spinetools.spine.authorurp", override[1]);
                        break;
                    case "authorUID":
                        System.setProperty("uk.nhs.digital.mait.spinetools.spine.authoruid", override[1]);
                        break;
                }
            }
            if (a.contentEquals("nosend")) {
                nosend = true;
            }
            if (a.contentEquals("dumpmessage")) {
                dumpmessage = true;
            }
        }
        if (System.getProperty("uk.nhs.digital.mait.spinetools.spine.listener.port") != null) {
            listenerPort = Integer.parseInt(System.getProperty("uk.nhs.digital.mait.spinetools.spine.listener.port"));
        }
        if (System.getProperty("uk.nhs.digital.mait.spinetools.spine.commandline.waitperiod") != null) {
            waitPeriod = Integer.parseInt(System.getProperty("uk.nhs.digital.mait.spinetools.spine.commandline.waitperiod"));
        }
        if (System.getProperty("uk.nhs.digital.mait.spinetools.spine.commandline.delayperiod") != null) {
            delayPeriod = Integer.parseInt(System.getProperty("uk.nhs.digital.mait.spinetools.spine.commandline.delayperiod"));
        }
        try {
            ConnectionManager cm = ConnectionManager.getInstance();
//            cm.loadPersistedMessages();
            SDSSpineEndpointResolver resolver = null;
            ArrayList<SdsTransmissionDetails> details = null;
            String ods = System.getProperty("uk.nhs.digital.mait.spinetools.spine.odstarget");
            switch (op) {
                case OP_RECEIVE:
                    cm.listen(listenerPort);
                    break;

                case OP_SENDALL:
                    // Directory, Service
                    sendAll(args[2], args[3], ods);
                    cm.stopListening();
                    break;

                case OP_SEND:
                    String msg = getFile(args[2]);
                    SpineHL7Message hl7 = new SpineHL7Message(msg, true);
                    send(hl7, args[3], ods);
                    sleep(waitPeriod);
                    cm.stopListening();
                    break;

                case OP_SENDPAYLOAD:
                    String p = getFile(args[2]);
                    SpineHL7Message msgpayload = new SpineHL7Message(args[3], p);
                    // TODO: Set ASIDs etc
                    msgpayload.setMyAsid(cm.getMyAsid());
                    send(msgpayload, args[4], ods);
                    sleep(waitPeriod);
                    cm.stopListening();
                    break;

                case OP_XMITDE:
                    String d = getFile(args[2]);
                    DistributionEnvelope de = DistributionEnvelopeHelper.getInstance().getDistributionEnvelope(d);
                    cm.listen(listenerPort);
                    sendDistributionEnvelope(de, ods);
                    sleep(waitPeriod);
                    cm.stopListening();
                    break;

                case OP_DEMO:
                    resolver = new SDSSpineEndpointResolver();
                    String destinationASID = null;
                    cm.listen(listenerPort);
                    details = resolver.getTransmissionDetails("urn:nhs:names:services:psis:REPC_IN150015UK05", "YES", null, null);
//                    details = resolver.getTransmissionDetails("urn:nhs:names:services:mm:PORX_IN020101UK31", "YES", null, null);
//                    details = resolver.getTransmissionDetails("urn:nhs:names:services:pdsquery:QUPA_IN000008UK05", "YEA", "908232220511", null);
//                    details = resolver.getTransmissionDetails("urn:nhs:names:services:pdsquery:QUPA_IN000006UK02", "YEA", "908232220511", null);
                    SdsTransmissionDetails psis = details.get(0);
                    StringBuilder scrPayload = new StringBuilder();

                    BufferedReader br = new BufferedReader(new FileReader("/home/damian/src/Test/JavaMHS/REPC_IN150015UK05_mhstest.xml"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        scrPayload.append(line);
                        scrPayload.append("\n");
                    }
                    br.close();
                    //SpineHL7Message msg = new SpineHL7Message("QUPA_IN000006UK02", scrPayload.toString());
                    SpineHL7Message message = new SpineHL7Message("REPC_IN150015UK05", scrPayload.toString());
//                    DistributionEnvelope d = DistributionEnvelopeHelper.getInstance().getDistributionEnvelope(de.toString());
//                    ITKDistributionEnvelopeAttachment deattachment = new ITKDistributionEnvelopeAttachment(d);

                    message.setMyAsid(cm.getMyAsid());
                    destinationASID = details.get(0).getAsids().get(0);

                    message.setToAsid(destinationASID);

                    // Set author details in msg
                    message.setAuthorRole("S0080:G0450:R5080");
                    message.setAuthorUid("687227875014");
                    message.setAuthorUrp("012345678901");

                    EbXmlMessage eb = new EbXmlMessage(psis, message);
//                      eb.addAttachment(deattachment);

                    // send the http message to the destination endpoint
                    cm.send(eb, psis);

                    // wait a decent delay for the async response
                    sleep(waitPeriod);

                    // tidy up
                    cm.stopListening();
                    cm.stopRetryProcessor();
                    //           } else {
                    // synchronous messaging
                    //            SpineSOAPRequest ssr = new SpineSOAPRequest(destinationEndpoint, msg);
                    //          cm.send(ssr, destinationEndpoint);
                    //    }

                    break;

//                case OP_SDS:
//                    String fullyQualifiedServiceName = args[2];
//                    ods = System.getProperty("uk.nhs.digital.mait.spinetools.spine.odstarget");
                // do an SDS lookup for the given interaction (fully qualified service name)
//                    details = resolver.getTransmissionDetails(fullyQualifiedServiceName, ods, null, null);
//                    System.out.println("SDS returned " + details.size() + " entries for " + fullyQualifiedServiceName + " at " + ods + "\r\n");
//                    for (SdsTransmissionDetails s : details) {
//                        for (java.lang.reflect.Method m : s.getClass().getMethods()) {
//                            Object[] a = new Object[0];
//                            if (m.getName().contains("get")) {
//                                System.out.println(m.getName().replaceFirst("^get", "") + " : " + m.invoke(s));
//                            }
//                        }
//                        System.out.println();
//                    }
//                    break;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     *
     * @param delay
     */
    private static void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(CommandLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void sendAll(String dir, String svc, String ods)
            throws Exception {

        File d = new File(dir);
        if (!d.isDirectory()) {
            System.out.println(dir + " is not a directory, nothing to do");
            return;
        }
        File[] files = d.listFiles();
        for (File f : files) {
            System.out.println(f.getAbsolutePath());
            String msg = getFile(f.getAbsolutePath());
            SpineHL7Message hl7 = new SpineHL7Message(msg, true);
            send(hl7, svc, ods);
            sleep(delayPeriod);
        }
        ConnectionManager.getInstance().stopListening();
        ConnectionManager.getInstance().stopRetryProcessor();
    }

    private static void send(SpineHL7Message hl7, String svc, String ods)
            throws Exception {
        if (System.getProperty("uk.nhs.digital.mait.spinetools.spine.authorrole") != null) {
            hl7.setAuthorRole(System.getProperty("uk.nhs.digital.mait.spinetools.spine.authorrole"));
        }
        if (System.getProperty("uk.nhs.digital.mait.spinetools.spine.authorurp") != null) {
            hl7.setAuthorUrp(System.getProperty("uk.nhs.digital.mait.spinetools.spine.authorurp"));
        }
        if (System.getProperty("uk.nhs.digital.mait.spinetools.spine.authoruid") != null) {
            hl7.setAuthorUid(System.getProperty("uk.nhs.digital.mait.spinetools.spine.authoruid"));
        }

        StringBuilder sb = new StringBuilder(svc);
        sb.append(":");
        sb.append(hl7.getInteractionId());
        String svcia = sb.toString();

        ConnectionManager cm = ConnectionManager.getInstance();
        SDSSpineEndpointResolver resolver = new SDSSpineEndpointResolver();
        ArrayList<SdsTransmissionDetails> details = resolver.getTransmissionDetails(svcia, ods, null, null);
        if (details.isEmpty()) {
            System.err.println("Failed to resolve " + svcia);
            return;
        }
        Sendable toSend = null;
        SdsTransmissionDetails sds = details.get(0);
        hl7.setToAsid(sds.getAsids().get(0));
        if (sds.isSynchronous()) {
            toSend = new SpineSOAPRequest(sds, hl7);
        } else {
            ConnectionManager.getInstance().listen(listenerPort);
            toSend = new EbXmlMessage(sds, hl7);
        }
        if (nosend) {
            if (dumpmessage) {
                toSend.write(System.out);
            }
            return;
        }
        cm.send(toSend, sds);
    }

    private static void sendDistributionEnvelope(DistributionEnvelope de, String ods)
            throws Exception {
        ConnectionManager cm = ConnectionManager.getInstance();
        SDSSpineEndpointResolver resolver = new SDSSpineEndpointResolver();
        ArrayList<SdsTransmissionDetails> details = resolver.getTransmissionDetails("urn:nhs:names:services:itk:COPC_IN000001GB01", ods, null, null);
        if (details.isEmpty()) {
            System.err.println("Failed to resolve urn:nhs:names:services:itk:COPC_IN000001GB01");
            return;
        }

        SdsTransmissionDetails sds = details.get(0);
        SpineHL7Message hl7 = new SpineHL7Message("urn:nhs:names:services:itk:COPC_IN000001GB01");
        ITKDistributionEnvelopeAttachment itkde = new ITKDistributionEnvelopeAttachment(de);
        EbXmlMessage ebxml = new EbXmlMessage(sds, hl7);
        ebxml.addAttachment(itkde);
        if (nosend) {
            if (dumpmessage) {
                ebxml.write(System.out);
            }
            return;
        }
        cm.send(ebxml, sds);

    }

    /**
     *
     * @param f the path to the file to be opened
     * @return a string containing the contents of the file
     * @throws Exception
     */
    private static String getFile(String f)
            throws Exception {
// 2012-08-07: Don't do it this way, which breaks HTTP headers and can
// invalidate content lengths
//        StringBuilder sb = new StringBuilder();
//        BufferedReader br = new BufferedReader(new FileReader(f));
//        String line = null;
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//            sb.append("\n");
//        }
//        br.close();
//        return sb.toString();

        File file = new File(f);
        FileReader fr = new FileReader(file);
        int size = (int) file.length();
        char[] buffer = new char[size];
        int justread = -1;
        int ptr = 0;
        while (ptr < size) {
            justread = fr.read(buffer, ptr, size - ptr);
            ptr += justread;
        }
        return new String(buffer);
    }

    private static void dumpVersionInfo() throws IOException {
        ClassLoader classLoader = CommandLine.class.getClassLoader();
        Properties properties = new Properties();
        properties.load(classLoader.getResourceAsStream("git.properties"));
        String versionString = String.format("SpineTools-%s %s %s",
                properties.getProperty("git.build.version"),
                properties.getProperty("git.commit.id.abbrev"),
                properties.getProperty("git.commit.time"));
        System.out.println(versionString);

        System.out.println("Conditional Compilation Control Settings:\r\n"
                + "LDAPOVERTLS:\t\t" + LDAPOVERTLS + "\r\n"
                + "LDAPS:\t\t\t" + LDAPS + "\r\n"
                + "OPENTEST:\t\t" + OPENTEST + "\r\n"
                + "TESTHARNESS:\t\t" + TESTHARNESS + "\r\n"
                + "DUMP_RECEIVED_MESSAGE:\t" + DUMP_RECEIVED_MESSAGE + "\r\n"
                + "cleartext:\t\t" + cleartext + "\r\n"
                + "otwMessageLogging:\t" + otwMessageLogging + "\r\n"
        );
    }
}
