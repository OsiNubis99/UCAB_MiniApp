package src.client;

import java.io.*;
import java.net.*;

class Main
{
   public static void main(String argv[]) throws Exception
   {
      String address = "127.0.0.1";
      String response;
      String msg;
      int port = 19876;
      BufferedReader inFromUser =
         new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Hello! Do you want to connect with UDP or TCP protocols?");
      System.out.println("\tUse 0 to the TCP protocol");
      System.out.println("\tUse 1 to the UDP protocol");
      String protocol = inFromUser.readLine();
      System.out.println("What is your username?");
      String username = inFromUser.readLine();
      if(protocol.equalsIgnoreCase("0")){
         Socket clientSocket = new Socket(address, port);
         PrintWriter outToServer =
            new PrintWriter(clientSocket.getOutputStream(), true);
         BufferedReader inFromServer =
            new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         outToServer.println("helloiam " + username);
         response = inFromServer.readLine();
         if(response.equalsIgnoreCase("OK")){
            System.out.println("Connection OK Please send a Message now.");
            while (true){
               System.out.println("Send 'exit' to finish this job");
               msg = inFromUser.readLine();
               outToServer.println(msg);
               if (msg.equalsIgnoreCase("exit")) break;
               response = inFromServer.readLine();
               System.out.println(response);
            }
         }else {
            System.out.println(response);
         }
         clientSocket.close();
      }else{
         DatagramSocket clientSocket = new DatagramSocket();
         InetAddress IPAddress = InetAddress.getByName(address);
         byte[] receiveData = new byte[1024];
         DatagramPacket receivePacket =
            new DatagramPacket(receiveData, receiveData.length);
         msg = "helloiam " + username;
         DatagramPacket sendPacket =
            new DatagramPacket(msg.getBytes(), msg.getBytes().length, IPAddress, port);
         clientSocket.send(sendPacket);
         clientSocket.receive(receivePacket);
         response = new String(receivePacket.getData(), 0, receivePacket.getLength());
         if(response.equalsIgnoreCase("OK")){
            System.out.println("Connection OK Please send a Message now.");
            while(true){
               System.out.println("Send 'exit' to finish this job");
               msg = inFromUser.readLine();
               sendPacket =
                  new DatagramPacket(msg.getBytes(), msg.getBytes().length, IPAddress, port);
               clientSocket.send(sendPacket);
               if (msg.equalsIgnoreCase("exit")) break;
               clientSocket.receive(receivePacket);
               response = new String(receivePacket.getData(), 0, receivePacket.getLength());
               System.out.println(response);
            }
         }else {
            System.out.println(response);
         }
         clientSocket.close();
      }
   }
}
