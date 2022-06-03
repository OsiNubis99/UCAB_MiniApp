package src.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import src.RMI.RMIInterface;

public class Main {
  private static final String IP = "127.0.0.1";
  private static final int PUERTO = 1100;

  public static void main(String[] args)
      throws RemoteException, NotBoundException {

    Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
    RMIInterface rmiServer = (RMIInterface)registry.lookup("MiniApp");

    Scanner sc = new Scanner(System.in);

    int eleccion;
    float numero1, numero2, resultado = 0;
    String menu = "[1] Send a new message\n[0] OUT!\n\nInsert an option: ";
    do {
      System.out.print(menu);
      try {
        eleccion = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        eleccion = 0;
      }
      if (eleccion != 0) {
        System.out.print("Send me your message: ");
        try {
          System.out.println(rmiServer.write(sc.nextLine()));
        } catch (NumberFormatException e) {
          numero1 = 0;
        }
      }
    } while (eleccion != 0);
  }
}
