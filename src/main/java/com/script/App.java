package com.script;

import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ){
        try{
            Scanner scanner = new Scanner(System.in);
            Socket socket = null;
            boolean kek = false;
            String message;
            while(!kek){
                kek = true;
                System.out.println("\nInsert the server's IP address");
                String address = scanner.nextLine();
                try{
                    socket = new Socket(address, 3000);
                }catch(UnknownHostException e){
                    System.out.println("\nServer not found");
                    kek = false;
                }
            }
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            ClientThread thread = new ClientThread(socket);
            thread.start();
            System.out.println("\nConnection established");
            do{
                message = scanner.nextLine();
                output.writeBytes(message);
            }while(message.equals("@exit"));
            scanner.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
