/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wb_client;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author zer0
 */
public class wb_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                wb_DrawBoard frame = new wb_DrawBoard();
                frame.setVisible(true);
                frame.setResizable(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }   
}
