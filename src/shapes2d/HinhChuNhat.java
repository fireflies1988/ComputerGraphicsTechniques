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
    
    public void draw(int virtualX, int virtualY, int virtualWidth, int virtualHeight, Graphics2D g2d) {
        int realX = MainFrame.getRealX(virtualX);
        int realY = MainFrame.getRealY(virtualY);
        int realWidth = MainFrame.getRealWH(virtualWidth);
        int realHeight = MainFrame.getRealWH(virtualHeight);
        
        for (int i = 0; i <= realWidth; i += MainFrame.PIXEL_SIZE) {
            g2d.fillRect(realX + i - offset, realY - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
            g2d.fillRect(realX + i - offset, realY + realHeight - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        }
        for (int i = 0; i <= realHeight; i += MainFrame.PIXEL_SIZE) {
            g2d.fillRect(realX - offset, realY + i - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
            g2d.fillRect(realX + realWidth - offset, realY + i - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        }
    }
    
    public void draw(Point virtualPt1, Point virtualPt2, Graphics2D g2d) {
        int virtualWidth = Math.abs(virtualPt1.x - virtualPt2.x);      
        int virtualHeight = Math.abs(virtualPt1.y - virtualPt2.y);
        
        // Find top left point
        // case: x1 <= x2 && y1 >= y2
        Point virtualTL = new Point(virtualPt1.x, virtualPt1.y);
        if (virtualPt1.x <= virtualPt2.x && virtualPt1.y <= virtualPt2.y) {
            // case: x1 <= x2 && y1 <= y2
            virtualTL = new Point(virtualPt1.x, virtualPt2.y);
        } else if (virtualPt1.x >= virtualPt2.x && virtualPt1.y <= virtualPt2.y) {
            // case: x1 >= x2 && y1 <= y2
            virtualTL = new Point(virtualPt2.x, virtualPt2.y);
        } else if (virtualPt1.x >= virtualPt2.x && virtualPt1.y >= virtualPt2.y) {
            // case: x1 >= x2 && y1 >= y2
            virtualTL = new Point(virtualPt2.x, virtualPt1.y);
        }
        
        draw(virtualTL.x, virtualTL.y, virtualWidth, virtualHeight, g2d);
    }
    
    // for rotating
    public void draw(Point virtualTL, Point virtualTR, Point virtualBL, Point virtualBR, Graphics2D g2d) {
        DoanThang line = new DoanThang();
        line.draw(virtualTL.x, virtualTL.y, virtualTR.x, virtualTR.y, g2d);
        line.draw(virtualTL.x, virtualTL.y, virtualBL.x, virtualBL.y, g2d);
        line.draw(virtualTR.x, virtualTR.y, virtualBR.x, virtualBR.y, g2d);
        line.draw(virtualBL.x, virtualBL.y, virtualBR.x, virtualBR.y, g2d);
    }

    
    
    
    
    // return new virtual coordinates of top left and bottom right points after scaling
    public ArrayList<Point> scale(Point virtualTL, Point virtualBR, float scaleX, float scaleY) {
        virtualTL.x = Math.round(virtualTL.x * scaleX);
        virtualTL.y = Math.round(virtualTL.y * scaleY);
        virtualBR.x = Math.round(virtualBR.x * scaleX);
        virtualBR.y = Math.round(virtualBR.y * scaleY);
        return new ArrayList<>(Arrays.asList(virtualTL, virtualBR));
    }
    
    // return new virtual coordinates of top left and bottom right points after scaling
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
        return new ArrayList<>(Arrays.asList(virtualTL, virtualBR));
    }
    
    public ArrayList<Point> translate(int virtualX, int virtualY, int virtualWidth, int virtualHeight, int virtualDx, int virtualDy) {
        Point virtualTL = new Point(virtualX, virtualY);
        Point virtualBR = new Point(virtualX + virtualWidth, virtualY - virtualHeight);
        return translate(virtualTL, virtualBR, virtualDx, virtualDy);
    }

    
    
    
    
    public ArrayList<Point> rotate(Point virtualTL, Point virtualBR, int angle) {
        int virtualWidth = Math.abs(virtualTL.x - virtualBR.x);
        int virtualHeight = Math.abs(virtualTL.y - virtualBR.y);
        Point virtualTR = new Point(virtualTL.x + virtualWidth, virtualTL.y);
        Point virtualBL = new Point(virtualTL.x, virtualTL.y - virtualHeight);
        
        virtualTL.x = (int) Math.round(virtualTL.x * Math.cos(Math.toRadians(angle)) - virtualTL.y * Math.sin(Math.toRadians(angle)));
        virtualTL.y = (int) Math.round(virtualTL.x * Math.sin(Math.toRadians(angle)) + virtualTL.y * Math.cos(Math.toRadians(angle)));
        virtualTR.x = (int) Math.round(virtualTR.x * Math.cos(Math.toRadians(angle)) - virtualTR.y * Math.sin(Math.toRadians(angle)));
        virtualTR.y = (int) Math.round(virtualTR.x * Math.sin(Math.toRadians(angle)) + virtualTR.y * Math.cos(Math.toRadians(angle)));
        virtualBL.x = (int) Math.round(virtualBL.x * Math.cos(Math.toRadians(angle)) - virtualBL.y * Math.sin(Math.toRadians(angle)));
        virtualBL.y = (int) Math.round(virtualBL.x * Math.sin(Math.toRadians(angle)) + virtualBL.y * Math.cos(Math.toRadians(angle)));
        virtualBR.x = (int) Math.round(virtualBR.x * Math.cos(Math.toRadians(angle)) - virtualBR.y * Math.sin(Math.toRadians(angle)));
        virtualBR.y = (int) Math.round(virtualBR.x * Math.sin(Math.toRadians(angle)) + virtualBR.y * Math.cos(Math.toRadians(angle)));
        return new ArrayList<Point>(Arrays.asList(virtualTL, virtualTR, virtualBL, virtualBR));
    }
    
    public ArrayList<Point> rotate(int virtualX, int virtualY, int virtualWidth, int virtualHeight, int angle) {
        Point virtualTL = new Point(virtualX, virtualY);
        Point virtualBR = new Point(virtualX + virtualWidth, virtualY - virtualHeight);
        return rotate(virtualTL, virtualBR, angle);
    }
}
