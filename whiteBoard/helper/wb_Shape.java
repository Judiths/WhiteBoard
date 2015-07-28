/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author zer0
 */
public class wb_Shape implements Serializable, Cloneable {
    public int type = -1;
    public Shape shape = null;
    public Color fontColor = Color.black;
    public Color backColor = Color.white;
    public Font font = null;
    public Vector<Point> vecPoints = new Vector<Point>();
    public Point oldPoint, newPoint;
    public String txt = null;
    public int penLength = 12;
    public int eraserLength = 15;
    public String msg = null;
    public wb_Shape() {}
    // 重置各个参数
    public void reset() {
        type = -1;
	shape = null;
        vecPoints.clear();
	oldPoint = null;
	newPoint = null;
	txt = null;
	msg = null;
    }
    public Object clone() {
        wb_Shape newShape = new wb_Shape();
	newShape.shape = this.shape;
	newShape.type = this.type;
	newShape.backColor = this.backColor;
	newShape.fontColor = this.fontColor;
	newShape.font = this.font;
	newShape.vecPoints = (Vector<Point>) this.vecPoints.clone();
	newShape.txt = this.txt;
	newShape.oldPoint = this.oldPoint;
        newShape.newPoint = this.newPoint;
        newShape.penLength = this.penLength;
	newShape.eraserLength = this.eraserLength;
	newShape.msg = this.msg;
	return newShape;
    }
}
