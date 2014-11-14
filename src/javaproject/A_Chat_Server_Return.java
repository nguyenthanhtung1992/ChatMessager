/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

/**
 *
 * 
 */
public class A_Chat_Server_Return implements Runnable{

    //Global
    Socket SOCK;
    private Scanner INPUT;
    private PrintWriter OUT;
    String MEASSAGE = "";
    
    public A_Chat_Server_Return(Socket X){
        this.SOCK = X;
        
    }
   public void CheckConnection() throws IOException{
       if(!SOCK.isConnected())
       {
           for(int i=1; i<A_Chat_Server.ConnectionArray.size(); i++){
               
               if(A_Chat_Server.ConnectionArray.get(i) == SOCK){
                   A_Chat_Server.ConnectionArray.remove(i);
                   
               }
           }
           for (int i=1; i<A_Chat_Server.ConnectionArray.size(); i++){
               Socket TEMP_SOCK= (Socket) A_Chat_Server.ConnectionArray.get(i);
               PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
               TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName()+ " Disconnected");
               TEMP_OUT.flush();
               ///////
               System.out.println(TEMP_SOCK.getLocalAddress().getHostName() + " Disconnected");
           }
   }
   }
    @Override
    public void run() {
       
        try{
        try {
            INPUT = new Scanner(SOCK.getInputStream());
            OUT = new PrintWriter(SOCK.getOutputStream());
            while(true){
                CheckConnection();
                if(!INPUT.hasNext()){
                    return;
                }
                MEASSAGE = INPUT.nextLine();
                System.out.println(" Clients said: " +MEASSAGE);
                
                for(int i=1; i< A_Chat_Server.ConnectionArray.size(); i++){
                Socket TEMP_SOCK = (Socket) A_Chat_Server.ConnectionArray.get(i);
                PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
                TEMP_OUT.println(MEASSAGE);
                TEMP_OUT.flush();
                System.out.println(" Sent to : " +TEMP_SOCK.getLocalAddress().getHostName());
                }
                        }
            }
        finally{
            SOCK.close();
        }
            
        } catch (Exception X) {
            System.out.println(X);
        }
        
   
    }
    
}

