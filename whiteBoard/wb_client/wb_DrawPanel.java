/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wb_client;

import helper.wb_Shape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.net.InetAddress;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author zer0
 */
public class wb_DrawPanel extends JPanel {
   
    int shapeType = -1; 						
    public Vector<wb_Shape> vecShapes;
    public wb_Shape tempShape;
    wb_ClienData clientData;				
    public static Color backColor = Color.white;
    
    public wb_DrawPanel() {
        vecShapes = new Vector<wb_Shape>();
	tempShape = new wb_Shape();
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;			
	super.paint(g);
	this.setBackground(backColor);			
        vecShapes.stream().forEach((wb_Shape temp) -> {
            drawShape(g2, (wb_Shape) temp.clone());
        });
	if(tempShape.type != -1)			
            drawShape(g2, tempShape);
    }
    
    public void drawShape(Graphics2D g2, wb_Shape shape) {
        g2.setColor(shape.fontColor);			
	g2.setStroke(new BasicStroke(shape.penLength)); 
	g2.setFont(shape.font);				
	int[][] points = null;
	if(shape.vecPoints.size() > 0) {
            points = new int[2][shape.vecPoints.size()];
            for(int i1 = 0; i1 < shape.vecPoints.size(); i1++) {
		points[0][i1] = shape.vecPoints.get(i1).x;
		points[1][i1] = shape.vecPoints.get(i1).y;
            }
	}
	switch(shape.type) {
            case 0: 
		g2.draw(shape.shape);
		break;
            case 1:
		if(shape.vecPoints.size() > 0)
                    g2.drawPolyline(points[0], points[1], shape.vecPoints.size());
                break;
            case 2:
		if(shape.vecPoints.size() > 1) {
                    for(int i = 0; i < shape.vecPoints.size(); i++) {
                        points[0][i] = (int) (points[0][i] + Math.pow(-1, i) * 3 * Math.sin(Math.PI * (100 * i - 180) / 180));
                        points[1][i] = (int) (points[1][i] + Math.pow(-1, i) * 3 * Math.cos(Math.PI * (100 * i - 180) / 180));
                    }
                    for(int i1 = 0; i1 < shape.vecPoints.size(); i1++)
			g2.drawLine(points[0][i1], points[1][i1], points[0][i1], points[1][i1]);
                }
		break;
            case 3:
		if(shape.vecPoints.size() > 1) {
                    Color old = g2.getColor();
                    g2.setColor(backColor);				
                    for(int i = 0; i < shape.vecPoints.size(); i++)
			g2.fillRect(points[0][i], points[1][i],shape.eraserLength, shape.eraserLength);
                    g2.setColor(old);
		}
		break;
            case 4: 
		int x = Math.min(shape.oldPoint.x, shape.newPoint.x),y = Math.min(shape.oldPoint.y, shape.newPoint.y);
		int weight = Math.abs(shape.oldPoint.x - shape.newPoint.x),height = Math.abs(shape.oldPoint.y - shape.newPoint.y);
		int xx[] = new int[4];
		int yy[] = new int[4];
		xx[0] = x + weight / 2;
		yy[0] = y;
		xx[2] = x + weight / 2;
		yy[2] = y + height;
		xx[1] = x;
		yy[1] = y + height / 2;
		xx[3] = x + weight;
		yy[3] = y + height / 2;
		g2.drawPolygon(xx, yy, 4);
		break;
            case 5:
		g2.drawString(shape.txt, shape.oldPoint.x, shape.oldPoint.y);
		break;
	}
    }
    public void Repaint(Color cB, Object cF) {
        vecShapes.add((wb_Shape) tempShape.clone());
	if(wb_DrawBoard.isOnNet == true)
            clientData.sendData((wb_Shape) tempShape.clone());
	tempShape.reset();
	if(cB != null) {
            backColor = cB;
            tempShape.backColor = cB;
            tempShape.type = 7;
            vecShapes.add((wb_Shape) tempShape.clone());
            if(wb_DrawBoard.isOnNet == true)
		clientData.sendData((wb_Shape) tempShape.clone());
            repaint();
	}
	tempShape.reset();
	if(cF != null)
            tempShape.fontColor = (Color) cF;       
    }

    public void connect(InetAddress address, int port, boolean just) {
        if(just == true) {
            clientData = new wb_ClienData(address, port);
            clientData.start();
	} else {
            wb_DrawBoard.drawPanel.sendMessage(wb_DrawBoard.userName
                    + "I'm leaving, next time!");
            clientData.close();
            wb_DrawBoard.isOnNet = false;
            System.out.println("haha23");
	}
    }
    public void sendMessage(String string) {
        wb_Shape shape = new wb_Shape();
	shape.msg = string;
	shape.type = 6;
	if(wb_DrawBoard.isOnNet == true)
            clientData.sendData((wb_Shape) shape.clone());
    }
    public void getMessage(String str) {
        wb_DrawBoard.jta.append(str + "\n");
    }
    public void drawShape(String shapeType, Point pOld, Point pNew, boolean isFinish) {
        switch(shapeType) {
            case "LINE":
		tempShape.shape = new Line2D.Double(pOld, pNew);
		tempShape.type = 0;
		break;
            case "ARC":
		tempShape.shape = new Arc2D.Double(Math.min(pOld.x,pNew.x), Math.min(pOld.y, pNew.y), Math.abs(pOld.x
                        - pNew.x), Math.abs(pOld.y - pNew.y),Math.min(pOld.x,pNew.x), Math.min(pOld.y, pNew.y),Arc2D.PIE);
                tempShape.type = 0;
		break;				
            case "RECT":
            	tempShape.shape = new Rectangle2D.Double(Math.min(pOld.x,pNew.x), Math.min(pOld.y, pNew.y), Math.abs(pOld.x
                        - pNew.x), Math.abs(pOld.y - pNew.y));
		tempShape.type = 0;
		break;
            case "ECLI":
		tempShape.shape = new Ellipse2D.Double(Math.min(pOld.x, pNew.x), Math.min(pOld.y, pNew.y),Math.abs(pOld.x - pNew.x), Math.abs(pOld.y - pNew.y));
                tempShape.type = 0;
                break;
            case "RRECT":
		tempShape.shape = new RoundRectangle2D.Double(Math.min(pOld.x,pNew.x), Math.min(pOld.y, pNew.y), Math.abs(pOld.x
			- pNew.x), Math.abs(pOld.y - pNew.y), 45, 45);
		tempShape.type = 0;
		break;
            case "PEN":
		tempShape.vecPoints.add(pNew);
		tempShape.type = 1;
		break;
            case "SPEN":
		tempShape.vecPoints.add(pNew);
		tempShape.type = 2;
		break;
            case "EASER":
		tempShape.vecPoints.add(pNew);
		tempShape.type = 3;
		break;
            case "DIAMOND":
		tempShape.oldPoint = (Point) pOld.clone();
		tempShape.newPoint = (Point) pNew.clone();
		tempShape.type = 4;
		break;
            case "TEXT":
		tempShape.txt = JOptionPane.showInputDialog("ENTER THE TEXT：", "PLEASE...");
		tempShape.oldPoint = (Point) pOld.clone();
		tempShape.type = 5;
		break;	
        }
	if(isFinish == true && tempShape.type <= 4 && tempShape.type >= 0) {
            vecShapes.add((wb_Shape) tempShape.clone());
            if(wb_DrawBoard.isOnNet == true)
                clientData.sendData((wb_Shape) tempShape.clone());
            tempShape.reset();
	}
	if(tempShape.type == 5) {
            vecShapes.add((wb_Shape) tempShape.clone());
            if(wb_DrawBoard.isOnNet == true)
                clientData.sendData((wb_Shape) tempShape.clone());
            tempShape.reset();
	}
        repaint();
    }

    public void getDataAndRepaint(wb_Shape wb_Shape) {
        if(wb_Shape.type == 6) {
            getMessage(wb_Shape.msg);
	}
        if(wb_Shape.type == 7) {
            backColor = wb_Shape.backColor;
            repaint();
        }
        vecShapes.add((wb_Shape) wb_Shape.clone());
	repaint();
    }
}
