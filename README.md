# devops-network-toolkit
This repo contains programs to perform different network operations like ping DNS, reverse DNS lookup, check for open ports, etc on different programming languages like Go, Java, Python, Javascript. The idea is to use these for containers/pods with the corresponding base image (Java, Python, NodeJS, Go, etc...) but which are lacking binaries to perform network operations like nslookup, ping, netstat or lsof binaries and you either don't want to install dnsutils, net-tools, bind-utils, bind-tools depending on the distro, you don't want to or can't run a pod with the specific utility (ping, nslookup, etc), or if you're behind a corporate proxy that makes it difficult to use apt-get or yum package managers.

Since some container images don't have vim installed, the idea would be to simply take advantage of the coreutils package to create a file with the code from inside the pod (using cat and heredocs strings), and then run the code with the already preconfigued development environment. I'll provide additional steps to use `cp` one liners from your local to copy the code inside the container in case coreutils isn't an option for some reason (?)


## Step 1. Log in to the pod 
```
kubectl exec -it <podname> -n <namespace> -- /bin/bash
```

## Step 2. Do a simple redirection of a heredocs string to a file
```
cat  <<EOF > mycode.lang
line1
line2
line3
EOF
```

### Step 2.1 - Here's an example on how to do this using the Ping.java found in the java/Ping directory from this repo:
```
cat <<EOF > Ping.java 
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
EOF
```

### Step 3. Then simply run the program with your prefered language:
```
java Ping.java mypod.svc.cluster.local
```




# APPENDIX

Here is a list of the package names that I think are needed to install nslookup, netstat, and lsof on some common Linux distributions. If you would like to submit a correction to any of these elements, feel free to reach me out and I'll make the correction.

## Ubuntu, Debian, and other Debian-based distributions:

* nslookup: dnsutils package
* *  `sudo apt-get install dnsutils`
* netstat: net-tools package
* * `sudo apt-get install net-tools`
* lsof: lsof package
* * `apt-get install lsof`

## RedHat, CentOS, Fedora, and other RedHat-based distributions:
* nslookup: bind-utils package
* * `sudo yum install bind-utils`
* netstat: net-tools package
* * `sudo yum install net-tools`
* lsof: lsof package
* * `sudo yum install lsof`

## Arch Linux:
* nslookup : bind-tools package
* * `sudo pacman -S bind-tools`
* netstat: net-tools package
* * `sudo pacman -S net-tools`
* lsof: lsof package 
* * `sudo pacman -S lsof`

Please keep in mind that some distributions uses different package manager like dnf or zypper instead of apt-get, yum or pacman.
