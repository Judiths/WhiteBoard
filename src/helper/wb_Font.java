/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import wb_client.wb_DrawBoard;

/**
 *
 * @author zer0
 */
public final class wb_Font implements ActionListener {
    JDialog fontDialog = new JDialog();
    JTextField tfFont = new JTextField(),
            tfStyle = new JTextField(),
            tfSize = new JTextField();
    JList listFont = new JList(),
          listStyle = new JList(),
          listSize = new JList();
    JLabel sample;
    JButton fontOkButton = new VectorButton(),
            cancelButton = new VectorButton();
    Container container = new Container();
    int[] fontStyleConst = {Font.PLAIN, Font.BOLD, Font.ITALIC,
			Font.BOLD + Font.ITALIC};;
    public wb_Font() {
	container = fontDialog.getContentPane();
	container.setLayout(new FlowLayout(FlowLayout.LEFT));
        Font currentFont = container.getFont();
        JLabel lblFont = new JLabel("Font (F):");
	lblFont.setPreferredSize(new Dimension(100, 20));
	JLabel lblStyle = new JLabel("Font Style(Y):");
	lblStyle.setPreferredSize(new Dimension(100, 20));
	JLabel lblSize = new JLabel("Font Size(S):");
	lblSize.setPreferredSize(new Dimension(100, 20));
        
        tfFont.setText(currentFont.getFontName());
	tfFont.selectAll();
	tfFont.setPreferredSize(new Dimension(200, 20));
        
        if(currentFont.getStyle() == Font.PLAIN)
            tfStyle.setText("Source Han Sans CN Normal");
	else if(currentFont.getStyle() == Font.BOLD)
            tfStyle.setText("Source Han Sans CN ExtraLight");
	else if(currentFont.getStyle() == Font.ITALIC)
            tfStyle.setText("Source Han Sans CN Light");
	else if(currentFont.getStyle() == (Font.BOLD + Font.ITALIC))
            tfStyle.setText("Source Han Sans CN Bold");
	tfStyle.setPreferredSize(new Dimension(200, 20));
        
        tfSize.setText(currentFont.getSize() + "");
	tfSize.setPreferredSize(new Dimension(200, 20));
        GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
	final String fontName[] = ge.getAvailableFontFamilyNames();
	int defaultFontIndex = 0;
        for(int i = 0; i < fontName.length; i++) {
            if(fontName[i].equals(currentFont.getFontName())) {
                defaultFontIndex = i;
		break;
            }
        }
	listFont = new JList(fontName);
	listFont.setSelectedIndex(defaultFontIndex);	
	listFont.setVisibleRowCount(7);
	listFont.setFixedCellWidth(82);
	listFont.setFixedCellHeight(20);
        listFont.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listFont.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                tfFont.setText(fontName[listFont.getSelectedIndex()]);
		tfFont.requestFocus();
		tfFont.selectAll();
		updateSample();
            }
        });
        final String fontStyle[] = {"Source Han Sans CN Normal", "Source Han Sans CN ExtraLight", 
            "Source Han Sans CN Light", "Source Han Sans CN Bold"};
	listStyle = new JList(fontStyle);
	listStyle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	if(currentFont.getStyle() == Font.PLAIN)
            listStyle.setSelectedIndex(0);
        else if(currentFont.getStyle() == Font.BOLD)
            listStyle.setSelectedIndex(1);
	else if(currentFont.getStyle() == Font.ITALIC)
            listStyle.setSelectedIndex(2);
	else if(currentFont.getStyle() == (Font.BOLD + Font.ITALIC))
            listStyle.setSelectedIndex(3);
	listStyle.setVisibleRowCount(7);
	listStyle.setFixedCellWidth(99);
	listStyle.setFixedCellHeight(20);
	listStyle.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                tfStyle.setText(fontStyle[listStyle.getSelectedIndex()]);
		tfStyle.requestFocus();
		tfStyle.selectAll();
		updateSample();
            }
	});     
        final String fontSize[] = {"8", "9", "10", "11", "12", "14", "16",
				"18", "20", "22", "24", "26", "28", "36", "48", "72"};
	listSize = new JList(fontSize);
	int defaultFontSizeIndex = 0;
        for(int i = 0; i < fontSize.length; i++) {
            if(fontSize[i].equals(currentFont.getSize() + "")) {
		defaultFontSizeIndex = i;
		break;
            }
	}
	listSize.setSelectedIndex(defaultFontSizeIndex);
	listSize.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listSize.setVisibleRowCount(7);
	listSize.setFixedCellWidth(39);
	listSize.setFixedCellHeight(20);
	listSize.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                tfSize.setText(fontSize[listSize.getSelectedIndex()]);
		tfSize.requestFocus();
		tfSize.selectAll();
		updateSample();
            }
        });
        fontOkButton.setFont(new Font("Dialog",Font.PLAIN,12));
        fontOkButton.setForeground(new Color(50,50,255));
        fontOkButton.setForeground(new Color(10,178,249));
        fontOkButton.setText("OK!");
        fontOkButton.setBorderPainted(false);	
	fontOkButton.addActionListener(this);
        
        cancelButton.setFont(new Font("Dialog",Font.PLAIN,12));
        cancelButton.setForeground(new Color(50,50,255));
        cancelButton.setForeground(new Color(182,0,55));
        cancelButton.setText("Cancle.");
        cancelButton.setBorderPainted(false);	
        cancelButton.addActionListener((ActionEvent e) -> {
            fontDialog.dispose();
        });
        sample = new JLabel("This is my sample.");
	sample.setHorizontalAlignment(SwingConstants.CENTER);
	sample.setPreferredSize(new Dimension(300, 50));
	
        JPanel samplePanel = new JPanel();
	samplePanel.setBorder(BorderFactory.createTitledBorder("Sample"));
	samplePanel.add(sample);
        fontDialog.setSize(350, 340);
	fontDialog.setLocation(200, 200);
	fontDialog.setResizable(false);
	fontDialog.setVisible(true);
        updateSample();
        container.add(lblFont);
	container.add(lblStyle);
	container.add(lblSize);
	container.add(tfFont);
	container.add(tfStyle);
	container.add(tfSize);
	container.add(fontOkButton);
	container.add(new JScrollPane(listFont));
	container.add(new JScrollPane(listStyle));
	container.add(new JScrollPane(listSize));
	container.add(cancelButton);
	container.add(samplePanel);
        
    }
    public void updateSample() {
        Font sampleFont = new Font(tfFont.getText(),
			fontStyleConst[listStyle.getSelectedIndex()],
			Integer.parseInt(tfSize.getText()));
	sample.setFont(sampleFont);
	}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fontOkButton) {
            wb_DrawBoard.drawPanel.tempShape.font = new Font(tfFont.getText(),
                         	fontStyleConst[listStyle.getSelectedIndex()],
            Integer.parseInt(tfSize.getText()));
            fontDialog.dispose();
	}
    }
    public static void main(String[] args) {
        wb_Font font = new wb_Font();
       
    }
}
