import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSLookup {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: DnsLookup <hostname>");
            System.exit(1);
        }

        String hostname = args[0];

        try {
            // Perform the DNS lookup
            InetAddress address = InetAddress.getByName(hostname);

            // Print the result
            System.out.println("Hostname: " + address.getHostName());
            System.out.println("IP address: " + address.getHostAddress());
        } catch (UnknownHostException e) {
            // If the hostname could not be resolved, print an error message
            System.out.println("Error: Unable to resolve hostname " + hostname);
        }
    }
}
