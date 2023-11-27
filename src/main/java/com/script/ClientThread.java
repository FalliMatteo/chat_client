package com.script;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket;

    public ClientThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        String input;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            do{
                input = in.readLine();
                System.out.println("\n" + input);
            }while(input.equals("ඞ"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}