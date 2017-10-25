import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class TcpReceiver {
    private static ServerSocket serverSocket;
    private static final int PORT = 1240;
    public static void main(String[] args)
    {
        System.out.println("Opening port\n");
        try
        {
            serverSocket = new ServerSocket(PORT);

			/*      Complete here     */


        }
        catch(IOException ioEx)
        {
            System.out.println(
                    "Unable to attach to port for receiver!");
            System.exit(1);
        }
        do
        {
            handleRouter();
        }while (true);
    }
    private static void handleRouter()
    {
        Socket link = null;
        try
        {
            link = serverSocket.accept();


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
                        "\n* Closing connectionÖ *");
                link.close();                     //Step 5.
            }
            catch(IOException ioEx)
            {
                System.out.println(
                        "Unable to disconnect!");
                System.exit(1);
            }
        }
    }

}
