package src.server;

import java.io.*;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;
import src.RMI.RMIInterface;

public class Main {
  public static String log(String message) {
    try {
      String actualDate =
          new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
      FileWriter lf = new FileWriter("server.log", true);
      BufferedWriter bw = new BufferedWriter(lf);
      bw.newLine();
      bw.write(actualDate + " -> Message recived: " + message);
      bw.close();
      return "\nMessage was saved\n";
    } catch (IOException e) {
      System.out.println("Error: " + e);
      return "There is a BUG!";
    }
  }

  private static final int PUERTO = 1100;

  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException {
    Remote remote = UnicastRemoteObject.exportObject(new RMIInterface() {
      @Override
      public String write(String message) throws RemoteException {
        return log(message);
      };
    }, 0);
    Registry registry = LocateRegistry.createRegistry(PUERTO);
    System.out.println("Servidor escuchando en el puerto " +
                       String.valueOf(PUERTO));
    registry.bind("MiniApp", remote);
  }
}
