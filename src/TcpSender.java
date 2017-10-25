//package org.test;
import java.io.*;
import java.net.*;
import java.util.*;

public class TcpSender {
    private static InetAddress host;
    private static final int PORT = 1241;
    public static void main(String[] args)
    {
        try
        {
            host = InetAddress.getLocalHost();
        }
        catch(UnknownHostException uhEx)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }
    private static void accessServer()
    {
        Socket link = null;
        try
        {
            link = new Socket(host,PORT);

		/*      Complete here     */

        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("\n* Closing connection*");
                link.close();                   //Step 4.
            }
            catch(IOException ioEx)
            {

                System.exit(1);
            }
        }
    }
}
