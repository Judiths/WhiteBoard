/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wb_server;

import helper.VectorButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author zer0
 */
public class wb_ServerPanel extends JPanel {
    private JTextField txtField = new JTextField();
    JTextArea txtArea = new JTextArea();
    public wb_ServerPanel(JTextArea txt) {
        this.txtArea = txt;
        // set JLabel
        JLabel label = new JLabel("PORT: ");
        label.setFont(new Font("Dialog", Font.PLAIN, 15));
        this.add(label);
        // set JTextField
        txtField.setFont(new Font("Dialog", Font.PLAIN, 18));
        txtField.setText("7800");
        txtField.setColumns(10);
        this.add(txtField);
        // set Button
        JButton btn1 = new VectorButton();
        btn1.setFont(new Font("Dialog", Font.PLAIN,16));
        btn1.setForeground(new Color(50,50,255));
        btn1.setForeground(new Color(97,211,47));
        btn1.setText("LISTEN");
        btn1.setBorderPainted(false);
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int port = Integer.parseInt(txtField.getText().trim());
                new wb_ServerDataTransit(port,txtArea).start();
                btn1.setEnabled(false);
            }
        });       
        this.add(btn1);
        
        JButton btn2 = new VectorButton();      
        btn2.setFont(new Font("Dialog", Font.PLAIN,16));
        btn2.setForeground(new Color(50,50,255));
        btn2.setForeground(new Color(238,0,40));
        btn2.setText("EXIT");
        btn2.setBorderPainted(false);
        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(btn2);
    }
}
