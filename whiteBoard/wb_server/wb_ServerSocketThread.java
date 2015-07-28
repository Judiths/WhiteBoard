/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wb_server;

import helper.wb_Shape;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author zer0
 */
public class wb_ServerSocketThread extends Thread {

    Socket socket = null;
    JTextArea txtArea = null;
    ObjectOutputStream objOut = null;
    ObjectInputStream objIn = null;
    public static Vector<wb_ServerSocketThread> vecAllThread = new Vector<wb_ServerSocketThread>();
    public wb_ServerSocketThread(Socket socket, JTextArea txtArea) {
        this.socket = socket;
        this.txtArea = txtArea;
        this.txtArea.append("\n"+socket.getInetAddress()+" connected.");
        vecAllThread.add(this);
    }
    @Override
    public void run() {
        try {
            objIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(wb_ServerSocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            objOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(wb_ServerSocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true) {
            try {
                wb_Shape shape = (wb_Shape) objIn.readObject();
                sendAllClientData(shape, this);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(this.socket.getInetAddress());
		txtArea.append("\n" + this.socket.getInetAddress() + "�ѶϿ���");
		this.stop();
		vecAllThread.remove(this);
		Logger.getLogger(wb_ServerSocketThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    /**
     *
     * @param shape
     * @param aThis
     */
    public void sendAllClientData(wb_Shape shape, wb_ServerSocketThread aThis) {
        vecAllThread.stream().filter((SST) -> (SST != aThis)).forEach((SST) -> {
            try {
                SST.objOut.writeObject(shape);
            } catch (IOException ex) {
                Logger.getLogger(wb_ServerSocketThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
