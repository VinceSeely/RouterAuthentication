/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacomprog3;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vince
 */
public class WebServer {
    
    ServerSocket servSock;
    /**
     * FactorialServer constructor creates an open listening port for the server
     * @param port 
     */
    public WebServer(int port)
    {
        try {
            servSock = new ServerSocket( port );
        } catch (IOException ex) {
            Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * opens the server and starts listening for inputs to the server on the 
     * given port number
     */
    public void run() 
    {
        try {            
            PrintWriter writer = new PrintWriter(
                    new FileWriter( "prog3.txt", true));
        
            while( true )
            {
                Socket sock;
                try {
                    sock = servSock.accept(); // listens for connections        
                    HTTPRequest servThread = new HTTPRequest( sock, writer ); 
                    servThread.start();
                } 
                catch (IOException ex) {
                    writer.println(ex.toString() + "\n");
                    writer.flush();
                }
            }
            
        }
        catch (IOException ex) {
            Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return the port that the socket is running on
     */
    public int getPort()
    {
        return servSock.getLocalPort();
    }
      
    /*
     * @return the IpAddress of the machine that this server will run on.
     */
    public String getIpAddress(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(WebServer.class.getName()).log(Level.SEVERE, null, ex);
            return "unknown";
        }
    }
}
