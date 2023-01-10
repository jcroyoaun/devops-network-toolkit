
# JAVA NETWORK TOOLKIT

## RMIPortCheckArgs.java
This helps troubleshoot a cluster of pods where we expect an RMI port to be open and there's a lengthy process in running the application and getting the Network error. 

RMI is a Java technology that allows the invokation of methods on remote objects (over the network) and is typically based on TCP.

If you have a Java application that requires to communicate with a remote server or container over an RMI port and you're in the middle of a troubleshooting to identify the broken piece of communication, having an application that specifically tests the availability of a port can help reduce the number of possibilities for failure.

### To copy the Java application to your pod or docker container
#### Kubernetes pod
```
kubectl cp RMIPortCheckArgs.java <namespace>/<podname>:<targetdirectory>
```

#### Docker container
```
docker cp [OPTIONS] SRC_PATH|- CONTAINER:DEST_PATH
```

#### To run the app
The application expects two arguments:
1. The first argument is the target hostname (podname, etc)
2. Second argument is the portnumber (RMI defaults to port 1099)
```
java RMIPortCheckArgs.java <hostname>.<service-name>.<namespace>.svc.cluster.local 1099
```

#### To determine the hostname 
```
kubectl exec <podname> -- sh -c 'hostname'; done
```
Usually the FQDN of hostnames in kubernetes clusters is:
```
<hostname>.<service-name>.<namespace>.svc.cluster.local
```
