package com.script;

import java.io.DataOutputStream;
import java.net.ConnectException;
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
                }catch(ConnectException e){
                    System.out.println("\nServer not found");
                    kek = false;
                }
            }
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            ClientThread thread = new ClientThread(socket);
            thread.start();
            System.out.println("\nConnection established");
            System.out.println("\nInsert your username");
            do{
                message = scanner.nextLine();
                if(message.isEmpty()){
                    System.out.println("\nYou can't send an empty string");
                }else{
                    output.writeBytes(message + "\n");
                }
            }while(!message.equals("@exit"));
            System.out.println("You closed the connection");
            scanner.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
