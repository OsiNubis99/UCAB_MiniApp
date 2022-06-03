package src.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
  String write(String message) throws RemoteException;
}
