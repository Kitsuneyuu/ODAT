/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucia Tortosa
 */
public class Client {

    static Socket cli;
    String cad;
    static boolean tanca = false;
    static boolean enviar = false;
    static OutputStream os = null;
    public static DataOutputStream dos = null;



    public Client()  {

        try {
            cli = new Socket("127.0.0.1", 9999);

            Lectura t = new Lectura(cli, this);
            t.start();

            os = cli.getOutputStream();
            dos = new DataOutputStream(os);


        } catch (UnknownHostException ex) {
            //System.out.println(ex.getMessage());
            
        } catch (IOException ex) {
            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                    "No hay conexión con el servidor. Envie un mensaje al administrador del sistema.",
                    "Sin conexión",
                    JOptionPane.ERROR_MESSAGE);
            DB.SQLiteJDBC.conectado = false;
        }

    }

    static class Lectura extends Thread {

        private Socket cli;
        private Client client;

        public Lectura(Socket cli, Client client) {
            this.cli = cli;
            this.client = client;
        }

        public void run() {
            String cad;
            int color;
            String nick;
            String cadena;
            InputStream is = null;

            try {
                is = cli.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                while (!tanca) {
                    cad = dis.readUTF();
                    if (!cad.contains("\t")) {
                        ODAT_Client.id = Integer.parseInt(cad);
                    } else if (cad.contains("\t")) {

                    } else {

                    }

                }

            } catch (IOException ex) {
                System.out.println("Error en lectura");
                System.out.println(ex);
            }

            try {
                is.close();
                cli.close();
                System.out.println("Hilo y sus conexiones correctamente cerrados");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
