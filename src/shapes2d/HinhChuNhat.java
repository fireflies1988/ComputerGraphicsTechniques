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
public class HinhChuNhat {
    private Point tl, tr, bl, br;
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
    
    // for rotating
    public void draw(Point tl, Point tr, Point bl, Point br, Graphics2D g2d) {
        DoanThang line = new DoanThang();
        line.draw(tl.x, tl.y, tr.x, tr.y, g2d);
        line.draw(tl.x, tl.y, bl.x, bl.y, g2d);
        line.draw(tr.x, tr.y, br.x, br.y, g2d);
        line.draw(bl.x, bl.y, br.x, br.y, g2d);
    }
    
    public ArrayList<Point> scale(Point virtualTL, Point virtualBR, float scaleX, float scaleY) {
        virtualTL.x = Math.round(virtualTL.x * scaleX);
        virtualTL.y = Math.round(virtualTL.y * scaleY);
        virtualBR.x = Math.round(virtualBR.x * scaleX);
        virtualBR.y = Math.round(virtualBR.y * scaleY);
        
        Point realTL = new Point(MainFrame.getRealX(virtualTL.x), MainFrame.getRealY(virtualTL.y));
        Point realBR = new Point(MainFrame.getRealX(virtualBR.x), MainFrame.getRealY(virtualBR.y));
        return new ArrayList<>(Arrays.asList(realTL, realBR));
    }
    
    public ArrayList<Point> scale(int virtualX, int virtualY, int virtualWidth, int virtualHeight, float scaleX, float scaleY) {
        Point virtualTL = new Point(virtualX, virtualY);
        Point virtualBR = new Point(virtualX + virtualWidth, virtualY - virtualHeight);
        return scale(virtualTL, virtualBR, scaleX, scaleY);
    }    
    
    public Point getCenterPoint(Point virtualTL, Point virtualBR) {
        return new Point(Math.round((virtualTL.x + virtualBR.x) / 2.0f), Math.round((virtualTL.y + virtualBR.y) / 2.0f));
    }
    
    public ArrayList<Point> translate(Point virtualTL, Point virtualBR, int virtualDx, int virtualDy) {
        virtualTL.x += virtualDx;
        virtualTL.y += virtualDy;
        virtualBR.x += virtualDx;
        virtualBR.y += virtualDy;
        
        Point realTL = new Point(MainFrame.getRealX(virtualTL.x), MainFrame.getRealY(virtualTL.y));
        Point realBR = new Point(MainFrame.getRealX(virtualBR.x), MainFrame.getRealY(virtualBR.y));
        return new ArrayList<>(Arrays.asList(realTL, realBR));
    }
}
