/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes2d;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import ktdhcuoiky.MainFrame;

/**
 *
 * @author COMPUTER
 */
public class DoanThang {
    private int x1, y1, x2, y2;
    private int offset = MainFrame.PIXEL_SIZE / 2;

    public DoanThang() {
    }
    
    public void draw(int virtualX1, int virtualY1, int virtualX2, int virtualY2, Graphics2D g2d) {
        // convert virtualX, virtualY to realX, realY
        int x1 = MainFrame.getRealX(virtualX1);
        int y1 = MainFrame.getRealY(virtualY1);
        int x2 = MainFrame.getRealX(virtualX2);
        int y2 = MainFrame.getRealY(virtualY2);
        
        // Bresenham
        int Dx, Dy, p, x, y;
        Dx = Math.abs(x2 - x1);
        Dy = Math.abs(y2 - y1);
           
        x = x1;
        y = y1;

        int xUnit = MainFrame.UNIT, yUnit = MainFrame.UNIT;
        if (x1 > x2) {
            xUnit = -xUnit;
        }
        if (y1 > y2) {
            yUnit = -yUnit;
        }
        
        g2d.fillRect(x - offset, y - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);  
        if (Dx >= Dy) {
            p = 2 * Dy - Dx;
            while (x != x2) {
                if (p < 0) p += 2 * Dy;
                else {
                    p += 2 * (Dy - Dx);
                    y += yUnit;
                }
                x += xUnit;
                g2d.fillRect(x - offset, y - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);  
            }
        } else {
            p = 2 * Dx - Dy;
            while (y != y2) {
                if (p < 0) p += 2 * Dx;
                else {
                    p += 2 * (Dx - Dy);
                    x += xUnit;
                }
                y += yUnit;
                g2d.fillRect(x - offset, y - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);  
            }
        }
    }
    
    public void draw(Point virtualPt1, Point virtualPt2, Graphics2D g2d) {
        draw(virtualPt1.x, virtualPt1.y, virtualPt2.x, virtualPt2.y, g2d);
    }
    
    
    
    
    public ArrayList<Point> translate(Point virtualPt1, Point virtualPt2, int virtualDx, int virtualDy) {
        Point pt1 = new Point(virtualPt1);
        Point pt2 = new Point(virtualPt2);
        
        pt1.x += virtualDx;
        pt1.y += virtualDy;
        pt2.x += virtualDx;
        pt2.y += virtualDy;
        
        return new ArrayList<>(Arrays.asList(pt1, pt2));
    }
    
    
    
    
    public ArrayList<Point> rotate(Point virtualPt1, Point virtualPt2, float angle) {
        Point pt1 = new Point(virtualPt1);
        Point pt2 = new Point(virtualPt2);
        
        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        
        int temp = pt1.x;
        pt1.x = Math.round(pt1.x * cos - pt1.y * sin);
        pt1.y = Math.round(temp * sin + pt1.y * cos);
        temp = pt2.x;
        pt2.x = Math.round(pt2.x * cos - pt2.y * sin);
        pt2.y = Math.round(temp * sin + pt2.y * cos);
        
        return new ArrayList<>(Arrays.asList(pt1, pt2));
    }
}
