/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacomprog3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vince
 */
public class HTTPRequest extends Thread{


    Socket sock;
    DataOutputStream writeSock;     // Used to write data to socket
    BufferedReader readSock;  
    PrintWriter logFile;
    
    /**
     * Thread constructor 
     * @param socketIn socket that it is listening on 
     * @param logFileStream contains information for file to write out to
     */
    public HTTPRequest(Socket socketIn, PrintWriter logFileStream)
    {
      
        sock = socketIn; 
        try {
            writeSock = new DataOutputStream(sock.getOutputStream());
            readSock = new BufferedReader(
                    new InputStreamReader(sock.getInputStream(), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {            
            logFile.println(ex);
            logFile.flush();
        } catch (IOException ex) {            
            logFile.println(ex);
            logFile.flush();
        }
        logFile = logFileStream;
    }
    
    /**
     * starts up the thread for the server and waits for input to be given. 
     * Returns the requested file to the server's out socket
     */
    @Override
    public void run()
    {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String log = "\nGot a connection: " + dateFormat.format(date) +
                    " IPAddress: " + sock.getInetAddress() + " Port: "
                    + sock.getPort();
            String message = readSock.readLine();
            log += "\n" + message;
            HTTP request = new HTTP();
            request.parseRequest(message);
            do
            {
                logFile.print(request.getFileName());
                byte[] httpMessage = request.returnHTTPMessage();
                try {
                    writeSock.write(httpMessage);
                    writeSock.flush();
                } catch (IOException ex) {
                    Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            while (request.getBytesRead()!= -1 && request.getFileExists());
            logFile.println(log);
            logFile.flush();
            sock.close();
        } catch (IOException ex) {
            logFile.println(ex);
            logFile.flush();
        }        
    }
}
