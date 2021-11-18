package src;

import java.io.*;

class Server extends Thread
{
  public int port;
  public String username;

  public void log(String message){
    try {
      FileWriter lf = new FileWriter("server.log", true);
      BufferedWriter bw = new BufferedWriter(lf);
      bw.newLine();
      bw.write(message);
      bw.close();
    } catch(IOException e) {
      System.out.println("Error: " + e);
    }
  }

  public String message(String msg, String who, String protocol){
    String[] words = msg.split(" ");
    switch (words[0])
      {
      case "helloiam":
        if(words.length >= 2)
          try {
            BufferedReader br=new BufferedReader(new FileReader("personas.txt"));
            String linea="";
            while ((linea = br.readLine())!=null) {
              if(linea.equalsIgnoreCase(words[1])) {
                this.username = words[1];
                this.log("Info: User " + this.username + " logs at " + java.time.LocalDateTime.now() + " from " + who + " with the " + protocol + " protocol");
                return "OK";
              }else {
              this.log("Warning: Bad user try to log at " + java.time.LocalDateTime.now() + " from " + who + " with user " + words[1]);
              return "Erorr: User isn't verificated";
              }
            }
          } catch(IOException e) {
            System.out.println("Error: " + e);
            return "Error 500";
          }
        this.log("Warning: " + who + " try to log at " + java.time.LocalDateTime.now() + " without any username");
        return "Error: The helloiam command need a username. Usage: '$ helloiam USERNAME'";
      default:
        this.log("Info: User " + this.username + " send: '" + msg + "' at " + java.time.LocalDateTime.now() + " from " + who + " with the " + protocol + " protocol");
        return "Connection OK, message saved.";
      }
  }
}
