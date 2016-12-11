/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacomprog3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
/**
 * Handles HTTP requests and acquires data from files
 * @author vince
 */
public class HTTP {
    private String contentTypeLine = "Content-type: ";
    private boolean fileExists = true;  
    private String fileName;
    private int byteToWrite = 1024;
    private FileInputStream tempFile;
    private int bytesRead;
    private int locInFile;
    private File file;
    
    /**
     * figures out what the request is looking for
     * @param requestData is the HTTP request
     * @throws FileNotFoundException if file not found throws exception
     */
    public void parseRequest(String requestData) throws FileNotFoundException
    {
        StringTokenizer temp = new StringTokenizer(requestData);
        String method = temp.nextToken();
        fileName = temp.nextToken();
        fileName = "." + fileName;
        FindFile();
        if (fileExists)
        {
            tempFile = new FileInputStream(file);
        }
    }

    /**
     * Composes a HTTP response stream
     * @return the response header 
     */
    private String composeResponse()
    {
        String retval ="HTTP/1.0 ";
        if(fileExists)
        {
            retval += "200 OK\r\n";
            generateContentType();
        }
        if (!fileExists)
        {
            retval += "404 Not Found\r\n";
        }
        return retval + contentTypeLine;
    }
        
    /**
     * figures out if the requested file exists on the server
     */
    private void FindFile()
    {
        file = new File(fileName);      
        fileExists = file.exists();
    }
    
    /**
     * @return File name trying to be found in this instance of the class
     */
    public String getFileName()
    {
        return fileName;
    }
    
    /**
     * @return total number of bytes read
     */
    public int getBytesRead()
    {
        return bytesRead;
    }
    
    /**
     * @return returns true if file exists and false otherwise
     */
    public boolean getFileExists()
    {
        return fileExists;
    }
    
    /**
     * @return the content type of the file
     */
    public String getContentTypeLine()
    {
        return contentTypeLine;
    }
    
    /**
     * gets the a compiles the file with a header. 
     * @return byte array with the file content
     * @throws IOException if there was nothing to read from the files stream
     */
    public byte[] returnHTTPMessage() throws IOException{
        if (fileExists)
        {
            byte[] temp = composeResponse().getBytes();
            byte[] retval = getFileData();
            byte[] acutual = new byte[1024];
            for (int i = 0; i < temp.length; i ++)
            {
                acutual[i] = temp[i];
            }
            for (int i = temp.length; i < acutual.length; i ++)
            {
                acutual[i] = retval[i - temp.length];
            }
            return retval;
        }
        else
        {
            String temp = composeResponse() + "text/html\r\n\r\n" + "<HTML>" + 
                    "<HEAD><TITLE>Not Found</TITLE></HEAD>"
                    + "<BODY>Not Found</BODY></HTML>"; 
            return temp.getBytes();        
        }
    }
    
    /**
     * returns the file data as a byte array
     * @return byte array with data from requested file
     * @throws IOException if unable to read from file 
     */
    public byte[] getFileData() throws IOException
    {
        byte[] retval = new byte[byteToWrite];
        //TODO: write file get data with an int input for start point in file
        bytesRead = tempFile.read(retval);
        if(bytesRead != -1)
            locInFile += bytesRead;
        return retval;
    }
    
    /**
     * creates the content type line and saves it to a variable
     */
    private void generateContentType()
    {
        if(fileName.endsWith(".htm") ||fileName.endsWith(".html")) 
            contentTypeLine = contentTypeLine + "text/html\r\n\r\n";
        else if(fileName.endsWith(".gif"))
        {
            contentTypeLine = contentTypeLine + "image/gif\r\n\r\n";
        }
        else if(fileName.endsWith(".bmb"))
        {
            contentTypeLine = contentTypeLine + "image/bmb\r\n\r\n";
        }
        else if(fileName.endsWith(".jpeg") || fileName.endsWith(".jpg"))
        {
            contentTypeLine = contentTypeLine + "image/jpeg\r\n\r\n";
        }
        else
        {
            contentTypeLine = contentTypeLine + "application/octet-stream\r\n\r\n";
        }
    } 
}
