package src;

import java.io.*;
import java.net.*;
import src.Server;

class TCPServer extends Server
{
  public TCPServer(int port){
    this.port = port;
  }

  public void run()
  {
    try {
      String msg;
      String response;
      ServerSocket welcomeSocket = new ServerSocket(this.port);
      System.out.println("The TCP server is running in port: " + this.port);
      while(true)
        {
          System.out.println("Waitng for any connection on TCP");
          Socket connectionSocket = welcomeSocket.accept();
          System.out.println("TCP connected");
          BufferedReader inFromClient =
            new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
          DataOutputStream outToClient =
            new DataOutputStream(connectionSocket.getOutputStream());
          while (true){
            System.out.println("Waitng for any message on TCP");
            msg = inFromClient.readLine();
            System.out.println("Message recived on TCP");
            if (msg.equalsIgnoreCase("exit")) break;
            response = this.message(msg, connectionSocket.getRemoteSocketAddress().toString(), "TCP") + '\n';
            outToClient.writeBytes(response);
            System.out.println("Response send on TCP");
          }
          connectionSocket.close();
          System.out.println("TCP disconnected");
          this.log("Info: TCP " + connectionSocket.getRemoteSocketAddress().toString() + " disconnected");
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
