/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes2d;

import java.awt.Graphics2D;
import java.awt.Point;
import ktdhcuoiky.MainFrame;

/**
 *
 * @author COMPUTER
 */
public class HinhChuNhat {
    private Point pt1, pt2;
    private int x, y, width, height;
    private int offset = MainFrame.PIXEL_SIZE / 2;

    public HinhChuNhat() {
    }
    
    public void draw(int x, int y, int width, int height, Graphics2D g2d) {
        for (int i = 0; i <= width; i += MainFrame.PIXEL_SIZE) {
            g2d.fillRect(x + i - offset, y - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
            g2d.fillRect(x + i - offset, y + height - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        }
        for (int i = 0; i <= height; i += MainFrame.PIXEL_SIZE) {
            g2d.fillRect(x - offset, y + i - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
            g2d.fillRect(x + width - offset, y + i - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        }
    }
    
    public void draw(Point pt1, Point pt2, Graphics2D g2d) {
        int width = Math.abs(pt1.x - pt2.x);      
        int height = Math.abs(pt1.y - pt2.y);
        
        // Find top left point
        // case: x1 <= x2 && y1 >= y2
        Point tl = new Point(pt1.x, pt1.y);
        if (pt1.x <= pt2.x && pt1.y >= pt2.y) {
            // case: x1 <= x2 && y1 <= y2
            tl = new Point(pt1.x, pt2.y);
        } else if (pt1.x >= pt2.x && pt1.y >= pt2.y) {
            // case: x1 >= x2 && y1 <= y2
            tl = new Point(pt2.x, pt2.y);
        } else if (pt1.x >= pt2.x && pt1.y <= pt2.y) {
            // case: x1 >= x2 && y1 >= y2
            tl = new Point(pt2.x, pt1.y);
        }
        
        draw(tl.x, tl.y, width, height, g2d);
    }
}
