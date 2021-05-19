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
    
    // for rotating and scaling
    public void draw(Point tl, Point tr, Point bl, Point br, Graphics2D g2d) {
        DoanThang line = new DoanThang();
        line.draw(tl.x, tl.y, tr.x, tr.y, g2d);
        line.draw(tl.x, tl.y, bl.x, bl.y, g2d);
        line.draw(tr.x, tr.y, br.x, br.y, g2d);
        line.draw(bl.x, bl.y, br.x, br.y, g2d);
    }
    
    public void scale(Point tl, Point tr, Point bl, Point br, float scaleX, float scaleY, Graphics2D g2d) {
        tl.x = MainFrame.roundX((int) (tl.x * scaleX));
        tl.y = MainFrame.roundY((int) (tl.y * scaleY));
        tr.x = MainFrame.roundX((int) (tr.x * scaleX));
        tr.y = MainFrame.roundY((int) (tr.y * scaleY));
        bl.x = MainFrame.roundX((int) (bl.x * scaleX));
        bl.y = MainFrame.roundY((int) (bl.y * scaleY));
        br.x = MainFrame.roundX((int) (br.x * scaleX));
        br.y = MainFrame.roundY((int) (br.y * scaleY));
        draw(tl, tr, bl, br, g2d);
    }
    
    public void scale(int x, int y, int width, int height, float scaleX, float scaleY, Graphics2D g2d) {
        Point tl = new Point(x, y);
        Point tr = new Point(x + width, y);
        Point bl = new Point(x, y + height);
        Point br = new Point(x + width, y + height);
        scale(tl, tr, bl, br, scaleX, scaleY, g2d);
    }
}
