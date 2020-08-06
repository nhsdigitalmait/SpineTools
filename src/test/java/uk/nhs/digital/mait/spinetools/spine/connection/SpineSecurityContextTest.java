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

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
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
public class SpineSecurityContextTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties
            = new RestoreSystemProperties();

    private SpineSecurityContext instance;

    private static ServerSocketFactory ssf;
    private static ServerSocket serverSocket;
    private static Thread thread;

    private static final int SERVER_LISTEN_PORT = 4848;
    private static final int CLIENT_LISTEN_PORT = 4000;

    public SpineSecurityContextTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        System.setProperty("org.warlock.http.spine.certs", System.getenv("TKWROOT") + "/contrib/Test_Certificates/TLS_Test_Certificates/Test01/test01.jks");
        System.setProperty("org.warlock.http.spine.sslcontextpass", "test01tls_moscow");

        ssf = ServerSocketFactory.getDefault();
        serverSocket = ssf.createServerSocket(SERVER_LISTEN_PORT);
        serverSocket.setReuseAddress(true);
        Runnable task = () -> {
            System.out.println("Listener thread started on " + SERVER_LISTEN_PORT);
            try {
                while (!thread.isInterrupted()) {
                    try {
                        System.out.println("Server listening on " + serverSocket);
                        Socket s = serverSocket.accept();
                        System.out.println("Accepting request from " + s);
                        InputStream is = s.getInputStream();
                        int c;
                        while ((c = is.read()) != -1) {
                            if (c == (int) '\n') {
                                System.out.println("\nServer closing socket");
                                s.close();
                                break;
                            }
                            System.out.print((char) c);
                        }
                    } catch (SocketException ex) {
                        System.out.println("Server socket exception " + ex.getMessage());
                    }
                }
                serverSocket.close();
                System.out.println("Thread terminating on request");
            } catch (IOException ex) {
                Logger.getLogger(SpineSecurityContextTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        thread = new Thread(task);
        thread.start();
    }

    @AfterClass
    public static void tearDownClass() throws IOException, InterruptedException {
        thread.interrupt();
        serverSocket.close();
    }

    @Before
    public void setUp() throws IOException {
        try {
            instance = new SpineSecurityContext();
        } catch (Exception ex) {
            Logger.getLogger(SpineSecurityContextTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() throws IOException {
    }

    /**
     * Test of setupTrustStore method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetupTrustStore() throws Exception {
        System.out.println("setupTrustStore");
        instance.setupTrustStore();
    }

    /**
     * Test of setupKeyStore method, of class SpineSecurityContext. if we add
     * setup a keystore it seems to set encrypted mode at the client end so
     * commented out
     *
     * @throws java.lang.Exception
     */
    //@Test
    public void testSetupKeyStore() throws Exception {
        System.out.println("setupKeyStore");
        instance.setupKeyStore();
    }

    /**
     * Test of createContext method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateContext() throws Exception {
        System.out.println("createContext");
        instance.createContext();
    }

    /**
     * Test of isReady method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testIsReady() throws Exception {
        System.out.println("isReady");
        boolean expResult = true;
        boolean result = instance.isReady();
        assertEquals(expResult, result);
    }

    /**
     * Test of init method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testInit() throws Exception {
        System.out.println("init");
        // conditional compilation returns immediately
        instance.init();
    }

    /**
     * Test of getSocketFactory method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSocketFactory() throws Exception {
        System.out.println("getSocketFactory");
        instance.createContext();
        SSLSocketFactory result = instance.getSocketFactory();
        assertNotNull(result);
    }

    /**
     * Test of getServerSocketFactory method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetServerSocketFactory() throws Exception {
        System.out.println("getServerSocketFactory");
        ServerSocketFactory result = instance.getServerSocketFactory();
        assertNotNull(result);
    }

    /**
     * Test of addCACertificate method, of class SpineSecurityContext. if we add
     * a cert it seems to set encrypted mode at the client end so commented out
     *
     * @throws java.lang.Exception
     */
    //@Test
    public void testAddCACertificate() throws Exception {
        System.out.println("addCACertificate");
        String certFile = System.getenv("TKWROOT") + "/contrib/Test_Certificates/TLS_Test_Certificates/Test01/test01.crt";
        instance.setupKeyStore();
        instance.addCACertificate(certFile);
    }

    /**
     * Test of createSocket method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateSocket_0args() throws Exception {
        System.out.println("createSocket");
        String expResult = "";
        Socket result = null;
        try {
            result = instance.createSocket();
            System.out.println(result.toString());
            // have to connect now
            result.connect(new InetSocketAddress("localhost", 4848));
            assertNotNull(result);
            result.getOutputStream().write("message from testCreateSocket_0args\n".getBytes());
        } catch (SocketException ex) {
            fail("Socket exception" + ex.getMessage());
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    /**
     * Test of createSocket method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateSocket_String_int() throws Exception {
        System.out.println("createSocket");
        String h = "localhost";
        int p = SERVER_LISTEN_PORT;
        String expResult = "127.0.0.1";
        Socket result = null;
        try {
            result = instance.createSocket(h, p);
            assertEquals(expResult, result.getLocalAddress().getHostAddress());
            result.getOutputStream().write("message from testCreateSocket_String_int\n".getBytes());
        } catch (SocketException ex) {
            fail("Socket exception" + ex.getMessage());
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    /**
     * Test of createSocket method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateSocket_4args_1() throws Exception, Throwable {
        System.out.println("createSocket");
        String h = "localhost";
        int p = SERVER_LISTEN_PORT;
        InetAddress la = InetAddress.getByName("localhost");
        int lp = CLIENT_LISTEN_PORT;
        String expResult = "127.0.0.1";
        Socket result = null;
        try {
            result = instance.createSocket(h, p, la, lp);
            assertEquals(expResult, result.getLocalAddress().getHostAddress());
            result.getOutputStream().write("message from testCreateSocket_4args_1\n".getBytes());
        } catch (SocketException ex) {
            fail("Socket exception" + ex.getMessage());
        } catch (Exception ex) {
            fail("Exception" + ex.getMessage());
        } finally {
            if (result != null) {
                System.out.println("ka " + result.getKeepAlive());
                System.out.println("so " + result.getSoLinger());
                System.out.println("to " + result.getSoTimeout());
                System.out.println("bo " + result.isBound());
                System.out.println("co " + result.isClosed());
                result.close();
            }
        }
    }

    /**
     * Test of createSocket method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateSocket_InetAddress_int() throws Exception {
        System.out.println("createSocket");
        InetAddress a = InetAddress.getByName("localhost");
        int p = SERVER_LISTEN_PORT;
        String expResult = "127.0.0.1";
        Socket result = instance.createSocket(a, p);
        result.getOutputStream().write("message from testCreateSocket_InetAddress_int\n".getBytes());
        assertEquals(expResult, result.getLocalAddress().getHostAddress());
        result.close();
    }

    /**
     * Test of createSocket method, of class SpineSecurityContext.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateSocket_4args_2() throws Exception {
        System.out.println("createSocket");
        InetAddress a = InetAddress.getByName("localhost");
        int p = SERVER_LISTEN_PORT;
        InetAddress la = InetAddress.getByName("localhost");
        int lp = CLIENT_LISTEN_PORT+1;
        String expResult = "127.0.0.1";
        Socket result = null;
        try {
            result = instance.createSocket(a, p, la, lp);
            result.getOutputStream().write("message from testCreateSocket_4args_2\n".getBytes());
            assertEquals(expResult, result.getLocalAddress().getHostAddress());
        } catch (SocketException ex) {
            fail("Socket exception " + ex.getMessage());
        } catch (Exception ex) {
            fail("Exception" + ex.getMessage());
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    /**
     * Test of getDefault method, of class SpineSecurityContext.
     */
    @Test
    public void testGetDefault() {
        System.out.println("getDefault");
        SocketFactory result = SpineSecurityContext.getDefault();
        assertNotNull(result);
    }

}
