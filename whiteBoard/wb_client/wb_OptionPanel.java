/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wb_client;

import helper.wb_Font;
import helper.VectorButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author zer0
 */
class wb_OptionPanel extends JPanel implements ActionListener, WindowListener {

    JPanel jpDrawMode = new JPanel();
    JPanel jpFontAndColor = new JPanel();
    JPanel jpDrawPen = new JPanel();
    JPanel jpShapeSelect = new JPanel();
    JPanel jpEaser = new JPanel();
    JLabel jbName = new JLabel("Username: ", JLabel.CENTER),
           jbMode = null,
           jbIP = new JLabel("IP: ", JLabel.CENTER),
           jbPort = new JLabel("Port:", JLabel.CENTER);
    
    JTextField jtfName = new JTextField("username"),
            jtfIP = new JTextField("127.0.0.1"),
            jtfPort = new JTextField("7800");
    JDialog jd = null;
    JButton btnLine = new VectorButton(),
            btnRect = new VectorButton(),
            btnRoundRect = new VectorButton(),
            btnEcli = new VectorButton(),
            btnPen = new VectorButton(),
            btnDiamond = new VectorButton(),
            btnEaser = new VectorButton(),
            btnText = new VectorButton(),
            btnSprayPen = new VectorButton(),   
            btnArc = new VectorButton(),
            btnConn = new VectorButton(),
            btnDisconn = new VectorButton(),
            btnLocal = new VectorButton(),
            btnNet = new VectorButton(),
            btnBackColor = new VectorButton(),
            btnForeColor = new VectorButton(),
            btnFont = new VectorButton();
    
    JSlider jsPen = new JSlider(SwingConstants.HORIZONTAL, 0, 30, 15),
            jsEaser = new JSlider(SwingConstants.HORIZONTAL, 0, 30, 15);
    ButtonGroup btnGDrawMode = new ButtonGroup(),
            btnGShapeSelect = new ButtonGroup();    
    
    public wb_OptionPanel() {
        /* internet connection button*/
        btnConn.setFont(new Font("Dialog",Font.PLAIN,16));
        btnConn.setForeground(new Color(50,50,255));
        btnConn.setForeground(new Color(50,255,0));
        btnConn.setText("Conn");
        btnConn.setBorderPainted(false);	
	btnConn.addActionListener(this);
        /*internet disconnection button*/
        btnDisconn.setFont(new Font("Dialog",Font.PLAIN,16));
        btnDisconn.setForeground(new Color(50,50,255));
        btnDisconn.setForeground(new Color(249,18,7));
        btnDisconn.setText("Disconn");
        btnDisconn.setBorderPainted(false);	
	btnDisconn.addActionListener(this);
        /*mode to local draw */
        btnLocal.setFont(new Font("Dialog",Font.PLAIN,16));
        btnLocal.setForeground(new Color(50,50,255));
        btnLocal.setForeground(new Color(248,12,62));
        btnLocal.setText("Local");
        btnLocal.setBorderPainted(false);	
	btnLocal.addActionListener(this);
        /*mode to connect the internet*/
        btnNet.setFont(new Font("Dialog",Font.PLAIN,16));
        btnNet.setForeground(new Color(50,50,255));
        btnNet.setForeground(new Color(53,254,102));
        btnNet.setText("Net");
        btnNet.setBorderPainted(false);	
	btnNet.addActionListener(this);
        
        jpDrawMode.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Mode", TitledBorder.CENTER,
				TitledBorder.CENTER, null, Color.RED));
        jpDrawMode.setLayout(new GridLayout(1, 2, 5, 5));
        jpDrawMode.add(btnLocal);
	jpDrawMode.add(btnNet);
        btnGDrawMode.add(btnLocal);
	btnGDrawMode.add(btnNet);
        this.add(jpDrawMode);
        
        jpShapeSelect.setLayout(new GridLayout(4,3,5,5));
	jpShapeSelect.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "Graphics Selection", TitledBorder.CENTER,
		TitledBorder.CENTER, null, Color.RED));
        jpShapeSelect.setPreferredSize(getSize());
        btnLine.setFont(new Font("Dialog",Font.PLAIN,16));
	btnLine.setForeground(new Color(50,50,255));
	btnLine.setForeground(new Color(200,54,239));
        btnLine.setText("LINE");
        btnLine.setBorderPainted(false);
	btnLine.addActionListener(this);
        
        btnRect.setFont(new Font("Dialog",Font.PLAIN,16));
        btnRect.setForeground(new Color(50,50,255));
        btnRect.setForeground(new Color(119,27,171));
        btnRect.setText("RECT");
        btnRect.setBorderPainted(false);	
	btnRect.addActionListener(this);
        
        btnRoundRect.setFont(new Font("Dialog",Font.PLAIN,16));
        btnRoundRect.setForeground(new Color(50,50,255));
        btnRoundRect.setForeground(new Color(23,24,233));
        btnRoundRect.setText("RRECT");
        btnRoundRect.setBorderPainted(false);	
	btnRoundRect.addActionListener(this);
        
        btnEcli.setFont(new Font("Dialog",Font.PLAIN,16));
        btnEcli.setForeground(new Color(50,50,255));
        btnEcli.setForeground(new Color(114,19,6));
        btnEcli.setText("ECLI");
        btnEcli.setBorderPainted(false);	
	btnEcli.addActionListener(this);
        
        btnPen.setFont(new Font("Dialog",Font.PLAIN,16));
        btnPen.setForeground(new Color(50,50,255));
        btnPen.setForeground(new Color(212,199,125));
        btnPen.setText("PEN");
        btnPen.setBorderPainted(false);	
	btnPen.addActionListener(this);
        
        btnDiamond.setFont(new Font("Dialog",Font.PLAIN,16));
        btnDiamond.setForeground(new Color(50,50,255));
        btnDiamond.setForeground(new Color(16,247,140));
        btnDiamond.setText("DIAMOND");
        btnDiamond.setBorderPainted(false);	
	btnDiamond.addActionListener(this);
        
        btnEaser.setFont(new Font("Dialog",Font.PLAIN,16));
        btnEaser.setForeground(new Color(50,50,255));
        btnEaser.setForeground(new Color(116,173,14));
        btnEaser.setText("EASER");
        btnEaser.setBorderPainted(false);	
	btnEaser.addActionListener(this);
        
        btnText.setFont(new Font("Dialog",Font.PLAIN,16));
        btnText.setForeground(new Color(50,50,255));
        btnText.setForeground(new Color(37,208,202));
        btnText.setText("TEXT");
        btnText.setBorderPainted(false);	
	btnText.addActionListener(this);
        
        btnSprayPen.setFont(new Font("Dialog",Font.PLAIN,16));
        btnSprayPen.setForeground(new Color(50,50,255));
        btnSprayPen.setForeground(new Color(157,126,229));
        btnSprayPen.setText("SPEN");
        btnSprayPen.setBorderPainted(false);	
	btnSprayPen.addActionListener(this);
        
        btnArc.setFont(new Font("Dialog",Font.PLAIN,16));
	btnArc.setForeground(new Color(50,50,255));
	btnArc.setForeground(new Color(249,96,153));
	btnArc.setText("ARC");
	btnArc.setBorderPainted(false);	
	btnArc.addActionListener(this);	
        
	jpShapeSelect.add(btnLine);
        jpShapeSelect.add(btnRect);
	jpShapeSelect.add(btnDiamond);
	jpShapeSelect.add(btnEcli);
	jpShapeSelect.add(btnEaser);
	jpShapeSelect.add(btnPen);
	jpShapeSelect.add(btnText);
	jpShapeSelect.add(btnRoundRect);
	jpShapeSelect.add(btnSprayPen);
	jpShapeSelect.add(btnArc);       
        this.add(jpShapeSelect);
        
        btnGShapeSelect.add(btnLine);
	btnGShapeSelect.add(btnRect);
	btnGShapeSelect.add(btnDiamond);
	btnGShapeSelect.add(btnEcli);
	btnGShapeSelect.add(btnEaser);
	btnGShapeSelect.add(btnPen);
	btnGShapeSelect.add(btnText);
	btnGShapeSelect.add(btnRoundRect);	
	btnGShapeSelect.add(btnSprayPen);
	btnGShapeSelect.add(btnArc);
        
        jsPen.setMajorTickSpacing(3);
	jsPen.setMinorTickSpacing(1);       
       	jsPen.setPaintTicks(true);
        jsPen.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                wb_DrawBoard.drawPanel.tempShape.penLength = jsPen.getValue();
            }
        });
        jpDrawPen.setPreferredSize(new Dimension(200, 50));
	jpDrawPen.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "PEN settings", TitledBorder.CENTER,
		TitledBorder.DEFAULT_POSITION, null, Color.RED));
        jpDrawPen.add(jsPen);	
        this.add(jpDrawPen);
        jsEaser.setMajorTickSpacing(6);
	jsEaser.setMinorTickSpacing(1);
	jsEaser.setPaintTicks(true);
        jsEaser.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                wb_DrawBoard.drawPanel.tempShape.eraserLength = jsEaser.getValue();
            }
        });
        jpEaser.setPreferredSize(new Dimension(200,50));
	jpEaser.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "EASER settings", TitledBorder.CENTER,
		TitledBorder.DEFAULT_POSITION, null, Color.RED));
        
        jpEaser.add(jsEaser);
        this.add(jpEaser);
     		
        btnBackColor.setFont(new Font("Dialog",Font.PLAIN,16));	
	btnBackColor.setForeground(new Color(50,50,255));
	btnBackColor.setForeground(new Color(200,54,239));
	btnBackColor.setText("BackColor settings");
	btnBackColor.setBorderPainted(false);
        btnBackColor.addActionListener(this);
        
        btnForeColor.setFont(new Font("Dialog",Font.PLAIN,16));
        btnForeColor.setForeground(new Color(50,50,255));
        btnForeColor.setForeground(new Color(50,255,0));
        btnForeColor.setText("ForeColor settings");
        btnForeColor.setBorderPainted(false);
        btnForeColor.addActionListener(this);
        
        btnFont.setFont(new Font("Dialog",Font.PLAIN,16));
        btnFont.setForeground(new Color(50,50,255));
        btnFont.setForeground(new Color(194,174,21));
        btnFont.setText("Font settings");
        btnFont.setBorderPainted(false);
        btnFont.addActionListener(this);

        jpFontAndColor.setLayout(new GridLayout(3, 1, 5, 5));
	jpFontAndColor.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "Font and Color",
		TitledBorder.CENTER, TitledBorder.CENTER, null, Color.RED));
        jpFontAndColor.setPreferredSize(new Dimension(200, 100));
        jpFontAndColor.add(btnBackColor);
        jpFontAndColor.add(btnForeColor);       
        jpFontAndColor.add(btnFont);
        this.add(jpFontAndColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Disconn":
                canConnect(false);
                jd.dispose();
                break;
            case "Conn":
                canConnect(true);
                jd.dispose();
                break;
            case "LINE":
                wb_DrawBoard.shapeType = "LINE";
                break;
            case "TEXT":
		wb_DrawBoard.shapeType = "TEXT";
		break;
            case "ECLI":
		wb_DrawBoard.shapeType = "ECLI";
		break;
            case "RECT":
		wb_DrawBoard.shapeType = "RECT";
		break;
            case "RRECT":
		wb_DrawBoard.shapeType = "RRECT";
		break;
            case "EASER":
		wb_DrawBoard.shapeType = "EASER";
		break;
            case "DIAMOND":
		wb_DrawBoard.shapeType = "DIAMOND";
		break;
            case "PEN":
		wb_DrawBoard.shapeType = "PEN";
		break;
            case "SPEN":
		wb_DrawBoard.shapeType = "SPEN";
		break;
            case "ARC":
		wb_DrawBoard.shapeType = "ARC";
		break;
            case "Net":
		showModeJDialog();
		break;
            case "Local":
		break;  
            case "BackColor settings":
		wb_DrawBoard.drawPanel.Repaint(
		JColorChooser.showDialog(this, "BackColor settings", Color.white),null);
		break;
            case "ForeColor settings":
		wb_DrawBoard.drawPanel.Repaint(null,
					JColorChooser.showDialog(this, "ForeColor settings", Color.black));
		break;
            case "Font settings":
		new wb_Font();
		break;
            default:
		wb_DrawBoard.shapeType = "PEN";	
		break;				
        }
    }
    public void showModeJDialog() {
        jd = new JDialog();
	jd.setTitle("White Board Online");
	jd.setSize(250, 200);
	jd.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	jd.setResizable(false);
	jd.getContentPane().setLayout(new GridLayout(4, 2, 5, 5));
	jd.getContentPane().add(jbName);
	jd.getContentPane().add(jtfName);
	jd.getContentPane().add(jbIP);
	jd.getContentPane().add(jtfIP);
	jd.getContentPane().add(jbPort);
	jd.getContentPane().add(jtfPort);
	jd.getContentPane().add(btnConn);
	jd.getContentPane().add(btnDisconn);
	jd.setVisible(true);

	final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	jd.setLocation(width / 2 - 125, height / 2 - 125);
	jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                jd.dispose();
            }
        }); 
    }
    public void canConnect(final boolean just) {
        if(just == false) {
            wb_DrawBoard.drawPanel.connect(null, -1, just);
	}
	wb_DrawBoard.userName = jtfName.getText().trim();
	String portstr = jtfPort.getText().trim();
	String IPstr = jtfIP.getText().trim(); // 得到IP，端口号
        System.out.println(IPstr);
	int port = Integer.valueOf(portstr);
	boolean temp = false;
        InetAddress address = null;
	try {
            address = InetAddress.getByName(IPstr);
            System.out.println(address.toString());
        } catch (UnknownHostException ex) {
            temp = true;
            JOptionPane.showMessageDialog(null, "Please check your IP: ", "IP",
            JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(wb_OptionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }	
	if(temp == false) {
            wb_DrawBoard.drawPanel.connect(address, port, just);
            wb_DrawBoard.isOnNet = true;
            wb_DrawBoard.drawPanel.sendMessage(wb_DrawBoard.userName
				+ "Hello !");
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}
