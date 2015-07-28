/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wb_client;

import helper.wb_Shape;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zer0
 */
public class wb_ClienData extends Thread {
    Socket socket;
    ObjectInputStream objIn;
    ObjectOutputStream objOut;
    public wb_ClienData(InetAddress address, int port) {
        try {
            socket = new Socket(address, port);
            wb_DrawBoard.isOnNet = true;
        } catch (IOException ex) {
            Logger.getLogger(wb_ClienData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            objOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(wb_ClienData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            objIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(wb_ClienData.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    @Override
    public void run() {
        while(true) {
            try {
                wb_DrawBoard.drawPanel.getDataAndRepaint((wb_Shape) objIn.readObject());
            } catch (IOException ex) {
                Logger.getLogger(wb_ClienData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(wb_ClienData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void sendData(wb_Shape tempShape) {
        try {
            objOut.writeObject(tempShape);
        } catch (IOException ex) {
            Logger.getLogger(wb_ClienData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void close() {
        try {
            objOut.close();
            objIn.close();
            socket.close();
            this.stop();
        } catch (IOException ex) {
            Logger.getLogger(wb_ClienData.class.getName()).log(Level.SEVERE, null, ex);
        }	
    }
}
