package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException 
    {

        Scanner sc = new Scanner(System.in);

        System.out.println("Il Client è stato avviato");
            
        Socket s = new Socket("localhost",3000);

        System.out.println("Il client si è collegato");

        do 
        {
            
            
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String message;
            do 
            {
                message = sc.nextLine();
                
                if(message!=null)
                {
                    out.writeBytes( message + "\n"); //messaggio da mandare al server
                }
            
                

                String stringaRicevuta = in.readLine(); //stringa ricevuta dal server

                if (stringaRicevuta.equals("!") ) 
                {
                    System.out.println("Connessione terminata");
                    break;
                }

                else if(message.equals("?"))
                {
                    while(!(stringaRicevuta.equals("@")))
                    {
                        System.out.println("La stringa restituita è: " + stringaRicevuta);

                        stringaRicevuta = in.readLine(); //stringa ricevuta dal server

                        if(stringaRicevuta.equals("@"))
                        {
                            break;
                        }
                    }
                }
                else
                {
                    System.out.println("La stringa restituita è: " + stringaRicevuta);
                }

                
            } while (!message.equals("!") );
            s.close();
            sc.close();
        }while (true);
        
    }
}