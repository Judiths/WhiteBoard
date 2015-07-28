package helper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zer0
 */
public class VectorButton extends JButton implements MouseListener{
    private boolean pressed = false;

    public VectorButton() {
        this.pressed = false;
        this.addMouseListener(this);
    }
    
    @Override
    public Dimension getPreferredSize() {
       String text = getText();
        FontMetrics fm = this.getFontMetrics(getFont());
        float scale = (50f/30f)*this.getFont().getSize2D();
        int w = fm.stringWidth(text);
        w += (int)(scale*1.4f);
        int h;
        h = fm.getHeight();
        h += (int)(scale*.3f);
        return new Dimension(w,h);    
    }

    @Override
    public void paintComponent(Graphics g) {        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(this.getBackground());
        g2.fillRect(0,0,this.getWidth(),this.getHeight());
        
        float scale = (50f/30f)*this.getFont().getSize2D();
        
        drawLiquidButton(this.getForeground(),
            this.getWidth(), this.getHeight(),
            getText(), scale,
            g2);
    }
    
    protected void drawLiquidButton(Color base, 
                int width, int height, 
                String text, float scale, 
                Graphics2D g2) {
        
        // calculate inset
        int inset = (int)(scale*0.04f);
        int w = width - inset*2 - 1;
        int h = height - (int)(scale*0.1f) - 1;
              
        g2.translate(inset,0);
        drawDropShadow(w,h,scale,g2);
        pressed = false;
        
        if(pressed) {
            g2.translate(0, 0.04f*scale);
        }

        drawButtonBody(w,h,scale,base,g2);
        drawText(w,h,scale,text,g2);
        drawHighlight(w,h,scale,base,g2);
        drawBorder(w,h,scale,g2);
        
        if(pressed) {
            g2.translate(0, 0.04f*scale);
        }
        g2.translate(-inset,0);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void drawDropShadow(int w, int h, float scale, Graphics2D g2) {
        g2.setColor(new Color(0,0,0,50));
        VectorButton.fillRoundRect(g2,
            (-.04f)*scale,
            (.02f)*scale,
            w+.08f*scale, h+0.08f*scale,
            scale*1.04f, scale*1.04f);
        g2.setColor(new Color(0,0,0,100));
        VectorButton.fillRoundRect(g2,0,0.06f*scale,w,h,scale,scale);
    }

    private void drawButtonBody(int w, int h, float scale, Color base, Graphics2D g2) {
        // draw the button body
        Color grad_top = base.brighter();
        Color grad_bot = base.darker();        
        GradientPaint bg = new GradientPaint(
            new Point(0,0), grad_top,
            new Point(0,h), grad_bot);
        g2.setPaint(bg);
        VectorButton.fillRoundRect(g2,
            (0)*scale,
            (0)*scale,
            w,h,1*scale,1*scale);
        
        // draw the inner color
        Color inner = base.brighter();
        inner = alphaColor(inner,75);
        g2.setColor(inner);
        VectorButton.fillRoundRect(g2, 
            scale*(.4f), 
            scale*(.4f), 
            w-scale*.8f, h-scale*.5f,
            scale*.6f,scale*.4f);
    }

    private void drawText(int w, int h, float scale, String text, Graphics2D g2) {
        // calculate the width and height
        int fw = g2.getFontMetrics().stringWidth(text);
        int fh = g2.getFontMetrics().getAscent() -
            g2.getFontMetrics().getDescent();
        int textx = (w-fw)/2;
        int texty = h/2 + fh/2;

        // draw the text
        g2.setColor(new Color(0,0,0,70));
        g2.drawString(text,(int)((float)textx+scale*(0.04f)), 
            (int)((float)texty + scale*(0.04f)));
        g2.setColor(Color.black);
        g2.drawString(text, textx, texty);
    }

    private void drawHighlight(int w, int h, float scale, Color base, Graphics2D g2) {
        // create the highlight
        GradientPaint highlight = new GradientPaint(
            new Point2D.Float(scale*0.2f,scale*0.2f),
            new Color(255,255,255,175),
            new Point2D.Float(scale*0.2f,scale*0.55f),
            new Color(255,255,255,0)
            );
        g2.setPaint(highlight);
        VectorButton.fillRoundRect(g2, scale*0.2f, scale*0.1f, 
            w-scale*0.4f, scale*0.4f, scale*0.8f, scale*0.4f);
        VectorButton.drawRoundRect(g2, scale*0.2f, scale*0.1f, 
            w-scale*0.4f, scale*0.4f, scale*0.8f, scale*0.4f);
    }

    private void drawBorder(int w, int h, float scale, Graphics2D g2) {
        // draw the border
        g2.setColor(new Color(0,0,0,150));
        VectorButton.drawRoundRect(g2,
            scale*(0f),
            scale*(0f),
            w,h,scale,scale);
    }

    private Color alphaColor(Color inner, int i) {
        return new Color(inner.getRed(), inner.getGreen(),
            inner.getBlue(), i);
    }
    // float version of fill round rect
    protected static void fillRoundRect(Graphics2D g2,
                float x, float y, 
                float w, float h, 
                float ax, float ay) {
        g2.fillRoundRect(
            (int)x, (int)y,
            (int)w, (int)h,
            (int)ax, (int)ay
            );
    }
    // float version of draw round rect
    protected static void drawRoundRect(Graphics2D g2,
                float x, float y, 
                float w, float h, 
                float ax, float ay) {
        g2.drawRoundRect(
            (int)x, (int)y,
            (int)w, (int)h,
            (int)ax, (int)ay
            );
    }
    
    public static void test(String s) {
        System.out.println(s);
    }
}
