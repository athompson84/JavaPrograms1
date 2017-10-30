//package org.test;
import java.io.*;
import java.net.*;
import java.util.*;

public class TcpRouter
{
    private static ServerSocket serverSocket;
    private static InetAddress host;
    private static final int PORT = 1241;
    private static final int PORT2 = 1240;
    private static Socket link2 = null;
    private static int counter = 0;
    private static int dropPacketSize = 0;

    public static void main(String[] args)
    {
        System.out.println("Opening port");
        {
            try
            {

               // Random roundInteger = new Random();
                //dropPacketSize = (int) Integer.valueOf(args[0])*14/100;
                //dropPacketSize += roundInteger.nextInt(2);

			/*      Complete here     */


                host = InetAddress.getLocalHost();
                System.out.println("Enter TcpReceiver IP Address");

			/*      Complete here     */

            }
            catch(UnknownHostException uhEx)
            {
                System.out.println("Host ID not found!");
                System.exit(1);
            }

        }
        try
        {

            serverSocket = new ServerSocket(PORT);  //Step 1.
            link2 = new Socket(host,PORT2);

			/*      Complete here     */
        }
        catch(IOException ioEx)
        {
            System.out.println(
                    "Unable to attach to port for router!");
            System.exit(1);
        }

        handleClient();

    }
    private static String handleClient()
    {
        Socket link = null;
        String str2 = null;

        try
        {
            link = serverSocket.accept();

            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            String message = input.nextLine();
            Scanner input = new Scanner(link2.getInputStream());
            PrintWriter output2 = new PrintWriter(link2.getOutputStream());

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
                System.out.println(
                        "\n* Closing connection�*");
                link.close();                     //Step 5.
            }
            catch(IOException ioEx)
            {
                System.out.println(
                        "Unable to disconnect!");
                System.exit(1);
            }
        }
        return null;
    }

}
