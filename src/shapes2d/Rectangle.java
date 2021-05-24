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
import computergraphicstechniques.MainFrame;

/**
 *
 * @author COMPUTER
 */
public class Rectangle extends Shape {
    public void draw(int virtualX, int virtualY, int virtualWidth, int virtualHeight, Graphics2D g2d) {
        int realX = MainFrame.getRealX(virtualX);
        int realY = MainFrame.getRealY(virtualY);
        int realWidth = MainFrame.getRealWH(virtualWidth);
        int realHeight = MainFrame.getRealWH(virtualHeight);
        
        for (int i = 0; i <= realWidth; i += MainFrame.UNIT) {
            g2d.fillRect(realX + i - offset, realY - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
            g2d.fillRect(realX + i - offset, realY + realHeight - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        }
        for (int i = 0; i <= realHeight; i += MainFrame.UNIT) {
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
        Line line = new Line();
        line.draw(virtualTL.x, virtualTL.y, virtualTR.x, virtualTR.y, g2d);
        line.draw(virtualTL.x, virtualTL.y, virtualBL.x, virtualBL.y, g2d);
        line.draw(virtualTR.x, virtualTR.y, virtualBR.x, virtualBR.y, g2d);
        line.draw(virtualBL.x, virtualBL.y, virtualBR.x, virtualBR.y, g2d);
    }

    
    
    
    
    // return new virtual coordinates of top left and bottom right points after scaling
    // used for zoom out, not good for zoom in
    public ArrayList<Point> scale(Point virtualTL, Point virtualBR, float scaleX, float scaleY) {
        Point tl = new Point(virtualTL);
        Point br = new Point(virtualBR);
        
        tl.x = (int) (tl.x * scaleX);  // type cast must be int, NOT Math.round
        tl.y = (int) (tl.y * scaleY);
        br.x = (int) (br.x * scaleX);
        br.y = (int) (br.y * scaleY);
        return new ArrayList<>(Arrays.asList(tl, br));
    }
    
    // used for zoom in
    public ArrayList<Point> scaleInc(Point virtualTL, Point virtualBR, float scaleX, float scaleY) {
        Point tl = new Point(virtualTL);
        Point br = new Point(virtualBR);
        
        tl.x = Math.round(tl.x * scaleX); 
        tl.y = Math.round(tl.y * scaleY);
        br.x = Math.round(br.x * scaleX);
        br.y = Math.round(br.y * scaleY);
        return new ArrayList<>(Arrays.asList(tl, br));
    }
    
    // return new virtual coordinates of top left and bottom right points after scaling
    public ArrayList<Point> scale(int virtualX, int virtualY, int virtualWidth, int virtualHeight, float scaleX, float scaleY) {
        Point virtualTL = new Point(virtualX, virtualY);
        Point virtualBR = new Point(virtualX + virtualWidth, virtualY - virtualHeight);
        return scale(virtualTL, virtualBR, scaleX, scaleY);
    }
    
    // for rotating
    public ArrayList<Point> scale(Point virtualTL, Point virtualTR, Point virtualBL, Point virtualBR, float scaleX, float scaleY) {
        Point tl = new Point(virtualTL);
        Point tr = new Point(virtualTR);
        Point bl = new Point(virtualBL);
        Point br = new Point(virtualBR);
        
        // type cast must be int, NOT Math.round (I got a problem here)
        tl.x = (int) (tl.x * scaleX);  
        tl.y = (int) (tl.y * scaleY);
        tr.x = (int) (tr.x * scaleX);  
        tr.y = (int) (tr.y * scaleY);
        bl.x = (int) (bl.x * scaleX);
        bl.y = (int) (bl.y * scaleY);
        br.x = (int) (br.x * scaleX);
        br.y = (int) (br.y * scaleY);
        return new ArrayList<>(Arrays.asList(tl, tr, bl, br));
    }
    

    
    
    
    
    public ArrayList<Point> translate(Point virtualTL, Point virtualBR, int virtualDx, int virtualDy) {
        Point tl = new Point(virtualTL);
        Point br = new Point(virtualBR);
        
        tl.x += virtualDx;
        tl.y += virtualDy;
        br.x += virtualDx;
        br.y += virtualDy;
        return new ArrayList<>(Arrays.asList(tl, br));
    }
    
    public ArrayList<Point> translate(Point virtualTL, Point virtualTR, Point virtualBL, Point virtualBR, int virtualDx, int virtualDy) {
        Point tl = new Point(virtualTL);
        Point tr = new Point(virtualTR);
        Point bl = new Point(virtualBL);
        Point br = new Point(virtualBR);
        
        tl.x += virtualDx;
        tl.y += virtualDy;
        tr.x += virtualDx;
        tr.y += virtualDy;
        bl.x += virtualDx;
        bl.y += virtualDy;
        br.x += virtualDx;
        br.y += virtualDy;
        return new ArrayList<>(Arrays.asList(tl, tr, bl, br));
    }
    
    public ArrayList<Point> translate(int virtualX, int virtualY, int virtualWidth, int virtualHeight, int virtualDx, int virtualDy) {
        Point virtualTL = new Point(virtualX, virtualY);
        Point virtualBR = new Point(virtualX + virtualWidth, virtualY - virtualHeight);
        return translate(virtualTL, virtualBR, virtualDx, virtualDy);
    }

    
    
    
    
    public ArrayList<Point> rotate(Point virtualTL, Point virtualBR, float angle) {
        int virtualWidth = Math.abs(virtualTL.x - virtualBR.x);
        int virtualHeight = Math.abs(virtualTL.y - virtualBR.y);
        Point virtualTR = new Point(virtualTL.x + virtualWidth, virtualTL.y);
        Point virtualBL = new Point(virtualTL.x, virtualTL.y - virtualHeight);
        
        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        
        Point tl = new Point(virtualTL);
        Point tr = new Point(virtualTR);
        Point bl = new Point(virtualBL);
        Point br = new Point(virtualBR);
        
        int temp = tl.x;
        tl.x = Math.round(tl.x * cos - tl.y * sin);
        tl.y = Math.round(temp * sin + tl.y * cos);
        temp = tr.x;
        tr.x = Math.round(tr.x * cos - tr.y * sin);
        tr.y = Math.round(temp * sin + tr.y * cos);
        temp = bl.x;
        bl.x = Math.round(bl.x * cos - bl.y * sin);
        bl.y = Math.round(temp * sin + bl.y * cos);
        temp = br.x;
        br.x = Math.round(br.x * cos - br.y * sin);
        br.y = Math.round(temp * sin + br.y * cos);
        
//        virtualTL.x = Math.round(virtualTL.x * cos - virtualTL.y * sin);
//        virtualTL.y = Math.round(virtualTL.x * sin + virtualTL.y * cos);
//        System.out.println(virtualTL);
//        virtualTR.x = Math.round(virtualTR.x * cos - virtualTR.y * sin);
//        virtualTR.y = Math.round(virtualTR.x * sin + virtualTR.y * cos);
//        System.out.println(virtualTR.y);
//        System.out.println(virtualTR);
//        virtualBL.x = Math.round(virtualBL.x * cos - virtualBL.y * sin);
//        virtualBL.y = Math.round(virtualBL.x * sin + virtualBL.y * cos);
//        System.out.println(virtualBL);
//        virtualBR.x = Math.round(virtualBR.x * cos - virtualBR.y * sin);
//        virtualBR.y = Math.round(virtualBR.x * sin + virtualBR.y * cos);
//        System.out.println(virtualBR);

        return new ArrayList<Point>(Arrays.asList(tl, tr, bl, br));
    }
    
    public ArrayList<Point> rotate(int virtualX, int virtualY, int virtualWidth, int virtualHeight, float angle) {
        Point virtualTL = new Point(virtualX, virtualY);
        Point virtualBR = new Point(virtualX + virtualWidth, virtualY - virtualHeight);
        return rotate(virtualTL, virtualBR, angle);
    }
    
    
    
    
    public Point getCenterPoint(Point virtualTL, Point virtualBR) {
        return new Point(Math.round((virtualTL.x + virtualBR.x) / 2.0f), Math.round((virtualTL.y + virtualBR.y) / 2.0f));
    }
    
    public Point getCenterPoint(int virtualX, int virtualY, int virtualWidth, int virtualHeight) {
        Point virtualTL = new Point(virtualX, virtualY);
        Point virtualBR = new Point(virtualX + virtualWidth, virtualY - virtualHeight);
        return getCenterPoint(virtualTL, virtualBR);
    }
    
    
    
    
    public ArrayList<Point> shear(Point virtualTL, Point virtualTR, Point virtualBL, Point virtualBR, float shearX, float shearY) {
        Point tl = new Point(virtualTL);
        Point tr = new Point(virtualTR);
        Point bl = new Point(virtualBL);
        Point br = new Point(virtualBR);
        
        tl.x += Math.round(shearX * tl.y);
        tl.y += Math.round(shearY * tl.x);
        tr.x += Math.round(shearX * tr.y);
        tr.y += Math.round(shearY * tr.x);
        bl.x += Math.round(shearX * bl.y);
        bl.y += Math.round(shearY * bl.x);
        br.x += Math.round(shearX * br.y);
        br.y += Math.round(shearY * br.x);
        
        return new ArrayList<Point>(Arrays.asList(tl, tr, bl, br));
    }
}
