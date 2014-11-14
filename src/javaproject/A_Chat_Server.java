/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tran Ba Y
 */
public class A_Chat_Server {
    
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    public static ArrayList<String> CurrentUsers = new ArrayList<String>();
    
    
    public static void main(String[] args) throws IOException{
        try{
            // Port 
        final int PORT = 8888;  
        ServerSocket SERVER = new ServerSocket(PORT);
        System.out.println(" Waiting for clients...");
        while(true)
        {
            Socket Socket = SERVER.accept();
            ConnectionArray.add(Socket); // add các socket
            System.out.println("Clients connect from: " +Socket.getLocalAddress().getHostName());
            AddUserName(Socket);
            A_Chat_Server_Return CHAT = new A_Chat_Server_Return(Socket);
            Thread X = new Thread(CHAT);
            X.start();
        }
        
        }
        catch(Exception X){
            System.out.print(X);
            
        }           
    }

    public static void AddUserName(Socket X) throws IOException {
        Scanner INPUT = new Scanner(X.getInputStream());
        String UserName = INPUT.nextLine();
        CurrentUsers.add(UserName);
        for (int i=1; i<A_Chat_Server.ConnectionArray.size(); i++){
            Socket TEMP_SOCK = (Socket) A_Chat_Server.ConnectionArray.get(i-1);
            PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
            OUT.println("#?!" +CurrentUsers);
            OUT.flush();
            
        } 
    }
}



