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
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            for (int i = 0; i<6; i++){
                System.out.println("How many packets");
                Scanner userEntry = new Scanner(System.in);
                String message,str2, response;
                int number;

                response = userEntry.nextLine();

                number = Integer.parseInt(response);

                int counter = 0;
                int attempt = 0;
                long statTime = System.nanoTime();

                do{
                   message = "PCk";

                   output.println(message+counter);
                   attempt++;

                   link.setSoTimeout(4000);

                   String str = input.nextLine();
                   str2 = str.substring(0,3);

                } while(!str2.equals("ACK")){
                }


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
