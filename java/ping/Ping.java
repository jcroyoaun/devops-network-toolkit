import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Ping <hostname>");
            System.exit(1);
        }
        String host = args[0];
        int timeout = 5000; // 5 seconds
        try {
            InetAddress address = InetAddress.getByName(host);
            boolean reachable = address.isReachable(timeout);
            if (reachable) {
                System.out.printf("Host %s is reachable\n", host);
            } else {
                System.out.printf("Host %s is not reachable\n", host);
            }
        } catch (UnknownHostException e) {
            System.err.printf("Unknown host: %s\n", host);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
