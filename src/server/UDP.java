package src.server;

import java.io.*;
import java.net.*;
import src.server.Server;

class UDP extends Server
{
  public UDP(int port){
    this.port = port;
  }

  public void run()
  {
    try {
      String msg;
      String response;
      DatagramSocket serverSocket = new DatagramSocket(this.port);
      byte[] receiveData = new byte[1024];
      byte[] sendData;
      System.out.println("The UDP server is running in port: " + this.port);
      while(true)
        {
          System.out.println("Waitng for any message on UDP");
          DatagramPacket receivePacket =
            new DatagramPacket(receiveData, receiveData.length);
          serverSocket.receive(receivePacket);
          msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
          InetAddress IPAddress = receivePacket.getAddress();
          int port = receivePacket.getPort();
          System.out.println("Message recived on UDP");
          response = this.message(msg, IPAddress.toString(), "UDP");
          sendData = response.getBytes();
          DatagramPacket sendPacket =
           new DatagramPacket(sendData, sendData.length, IPAddress, port);
          serverSocket.send(sendPacket);
          System.out.println("Response send on UDP");
        }
    } catch(SocketException e) {
      System.out.println("Error: " + e);
      this.log("Error: " + e);
    } catch(IOException e) {
      System.out.println("Error: " + e);
      this.log("Error: " + e);
    }
  }
}
