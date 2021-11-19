package src.server;

import src.server.TCP;
import src.server.UDP;

public class Main 
{

  public static void main(String[] args) {
    TCP tcpServer = new TCP(19876);
    UDP udpServer = new UDP(19876);
    tcpServer.start();
    udpServer.start();
  }
}
