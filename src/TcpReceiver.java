import java.io.BufferedReader;
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
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            int numMessages = 0;
            String message = input.nextLine();
            output.println(message);
            while(!message.equals("***CLOSE***"))
            {
                //if(!message != null)
                numMessages++;
                System.out.println(numMessages + ":" + message);
                message = input.nextLine();
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
                System.out.println(
                        "\n* Closing connectionÖ *");
                link.close();
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
