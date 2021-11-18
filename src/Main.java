package src;

import src.TCPServer;
import src.UDPServer;


public class Main 
{

  public static void main(String[] args) {
    TCPServer tcps = new TCPServer(19876);
    UDPServer udps = new UDPServer(19876);
    tcps.start();
    udps.start();
  }
}
