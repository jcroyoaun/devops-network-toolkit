import java.net.Socket;

public class RMIPortCheckArgs {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: RmiPortCheck <hostname> <port>");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            // Attempt to create a socket connection to the RMI server on the specified host and port
            Socket socket = new Socket(hostname, port);

            System.out.println("RMI port is open");

            // If the connection was successful, the RMI port is open
            socket.close();
        } catch (Exception e) {
            // If there was an error creating the socket connection, the RMI port is closed or unavailable
            System.out.println("RMI port is closed or unavailable");
        }
    }
}
