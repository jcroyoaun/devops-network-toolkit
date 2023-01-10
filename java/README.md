
# JAVA NETWORK TOOLKIT

## RMIPortCheckArgs
This helps troubleshoot a cluster of pods where we expect an RMI port to be open and there's a lengthy process in running the application and getting the Network error. 

RMI is a Java technology that allows the invokation of methods on remote objects (over the network) and is typically based on TCP.

If you have a Java application that requires to communicate with a remote server or container over an RMI port and you're in the middle of a troubleshooting to identify the broken piece of communication, having an application that specifically tests the availability of a port can help reduce the number of possibilities for failure.
