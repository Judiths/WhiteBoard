package wb_server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zer0
 */
public class wb_Server extends JFrame {
    private final JPanel contentPane =  new JPanel();
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                wb_Server frame = new wb_Server();
                frame.setVisible(true);
                frame.setResizable(true);
            }
        
        });
    } 
    public wb_Server() {
        setTitle("WhiteBoard Server");
        setBounds(100,100,600,500);
        contentPane.setBorder(new EmptyBorder(2,2,2,2));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);
        // set JTextArea
        JTextArea txt = new JTextArea();
        txt.setText("Server log");
        txt.setToolTipText("");
        txt.setForeground(Color.red);
        txt.setBackground(Color.white);
        txt.setFont(new Font("VL Gothic regular", Font.PLAIN,18));
        // set JScrollPane
        JScrollPane scroll = new JScrollPane(txt);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        txt.setLineWrap(false);
        contentPane.add(scroll, BorderLayout.CENTER);
        // set wb_Panel
        wb_ServerPanel panel = new wb_ServerPanel(txt);
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
             
    }
}
