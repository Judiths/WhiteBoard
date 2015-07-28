/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wb_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author zer0
 */
public class wb_ServerDataTransit extends Thread {

    ServerSocket serversocket = null;
    JTextArea txtArea = null;
    public wb_ServerDataTransit(int port, JTextArea txtArea) {
        this.txtArea = txtArea;
        try {
            serversocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(wb_ServerDataTransit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        while(true) {
            Socket socket = null;
            try {
                socket = serversocket.accept();
            } catch (IOException ex) {
                Logger.getLogger(wb_ServerDataTransit.class.getName()).log(Level.SEVERE, null, ex);
            }
            new wb_ServerSocketThread(socket,txtArea).start();
            System.out.println(wb_ServerDataTransit.class.getName()+"\n"+txtArea);
        }
    }
    
}
