/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wb_client;

import helper.VectorButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 *
 * @author zer0
 */
public class wb_DrawBoard extends JFrame {
    
    private wb_OptionPanel optionPanel;
    private final JPanel jpMessage;
    private final VectorButton jbSend,jbClear;    
    private JTextField jtf;
    private final JScrollPane jscr;
    private final JPanel panel;
    private Point pSrc,pDes,pTmpNew,pTmpOld;
    public static String shapeType = "PEN";
    public static JTextArea jta;
    public static String userName = "username";
    public static wb_DrawPanel drawPanel;
    public static boolean isOnNet = false;
    public wb_DrawBoard() {
        setResizable(false);
	setTitle("WhiteBoard Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 628);
        /* option panel*/
        optionPanel = new wb_OptionPanel();
        optionPanel.setLayout(new GridLayout(5,1,10,10));
        getContentPane().add(optionPanel, BorderLayout.WEST);
        /* draw panel*/
        drawPanel = new wb_DrawPanel();
        getContentPane().add(drawPanel, BorderLayout.CENTER);
        
        /* communication zone   */
	jpMessage = new JPanel();
	jbSend = new VectorButton();	
	jbSend.setFont(new Font("Dialog",Font.PLAIN,16));
        jbSend.setForeground(new Color(50,50,255));
        jbSend.setForeground(new Color(50,255,0));
        jbSend.setText("send");
        jbSend.setBorderPainted(false);	
	jbSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		String str = jtf.getText().trim();
		jta.append("local: " + str + "\n");
		jtf.setText("");
		if(isOnNet == true)
                    drawPanel.sendMessage(userName + ":" + str + "\n");
            }
	});
		
        jbClear = new VectorButton();
	jbClear.setFont(new Font("Dialog",Font.PLAIN,16));
	jbClear.setForeground(new Color(50,50,255));
	jbClear.setForeground(new Color(218,13,119));
	jbClear.setText("clear");
	jbClear.setBorderPainted(false);
	jbClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		jtf.setText("");
		jta.setText("");
            }
	});
	
	jtf = new JTextField();
	jtf.setForeground(Color.BLUE);
	jtf.setFont(new Font("黑体", Font.PLAIN, 16));
	jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String str = jtf.getText().trim();
                    jta.append("local: " + str + "\n");
                    jtf.setText("");
                    if(isOnNet == true)
                        drawPanel.sendMessage(userName + ":" + str + "\n");
                }
            }
		
        });
	jta = new JTextArea();
	jta.setForeground(new Color(63,14,243));
	jta.setFont(new Font("黑体", Font.PLAIN, 16));
	// 把定义的JTextArea放到JScrollPane里面去
	jscr = new JScrollPane(jta);
	jscr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	jscr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	jta.setLineWrap(true);
	jpMessage.setLayout(new GridLayout(2, 1, 0, 0));
	
	panel = new JPanel();
	jpMessage.add(jtf);
	jpMessage.add(panel);
	panel.add(jbSend);
	panel.add(jbClear);
	JPanel chat = new JPanel();
	chat.setBorder(new TitledBorder(UIManager
		.getBorder("TitledBorder.center"), "Communication Zone", TitledBorder.CENTER,
		TitledBorder.CENTER, null, new Color(63,14,243)));
	chat.setLayout(new BorderLayout());
	chat.setPreferredSize(new Dimension(200, 100));
	chat.add(jscr);
	chat.add(jpMessage, BorderLayout.SOUTH);
	// 加载聊天到右边
	getContentPane().add(chat, BorderLayout.EAST);
	// 监听主窗口的关闭事件，主要是释放相关资源
	addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
		if(isOnNet == false)
                    return;
		else
                    optionPanel.canConnect(false);
            }
        });
        /* paint board*/
        pTmpOld = new Point(0, 0);
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pSrc = e.getPoint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                pDes = e.getPoint();
                pTmpOld.x = pSrc.x;pTmpOld.y = pSrc.y;
		drawPanel.drawShape(shapeType, pTmpOld, pDes, true);
            }
								
        });
        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                pTmpNew = e.getPoint();
                pTmpOld.x = pSrc.x;
		pTmpOld.y = pSrc.y;
		drawPanel.drawShape(shapeType,(Point) pTmpOld.clone(), pTmpNew, false);
            }
			                              
        });
    }
    public static void main(String[] args) {
        wb_DrawBoard board = new wb_DrawBoard();
        board.setVisible(true);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
    }
}
