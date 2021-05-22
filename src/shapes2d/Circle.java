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
public class Circle {
    private int x, y, r;
    private int offset = MainFrame.PIXEL_SIZE / 2;

    public Circle() {
    }
    
    public void put8Pixels(int x, int y, int xC, int yC, Graphics2D g2d) {
        // goc phan tu I
        g2d.fillRect(x + xC - offset, y + yC - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        g2d.fillRect(y + xC - offset, x + yC - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        // goc phan tu II
        g2d.fillRect(-x + xC - offset, y + yC - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        g2d.fillRect(-y + xC - offset, x + yC - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        // goc phan tu IV
        g2d.fillRect(x + xC - offset, -y + yC - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        g2d.fillRect(y + xC - offset, -x + yC - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        // goc phan tu III
        g2d.fillRect(-x + xC - offset, -y + yC - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        g2d.fillRect(-y + xC - offset, -x + yC - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
    }
    
    public void draw(int virtualXC, int virtualYC, int virtualR, Graphics2D g2d) {
        // Convert virtualX, virtualY to realX, realY
        int xC = MainFrame.getRealX(virtualXC);
        int yC = MainFrame.getRealY(virtualYC);
        int r = MainFrame.getRealWH(virtualR);
                
        // Midpoint
        int x = 0, y = r;
        int f = 1 - r; // f = 5/4 - r vi 5/4 = 1

        int count = 0;
        put8Pixels(x, y, xC, yC, g2d); // putpixel va tinh tien hinh tron tai (0, 0) sang (xC, yC) 

        while (x < y) {
            x += MainFrame.UNIT;
            if (f < 0) f += 2 * x + 3;
            else {
                f += 2 * (x - y) + 5;
                y -= MainFrame.UNIT;
            }
            put8Pixels(x, y, xC, yC, g2d);
        }
    }
    
    public void draw(Point virtualPt, int virtualR, Graphics2D g2d) {
        draw(virtualPt.x, virtualPt.y, virtualR, g2d);
    }
    
    
    
    
    
    public Point translate(int virtualX, int virtualY, int virtualDx, int virtualDy) {
        return new Point(virtualX + virtualDx, virtualY + virtualDy);
    }
    public Point translate(Point virtualPt, int virtualDx, int virtualDy) {
        return new Point(virtualPt.x + virtualDx, virtualPt.y + virtualDy);
    }
    
    
    
    
    
    
    public Point rotate(int virtualX, int virtualY, float angle) {
        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        
        Point virtualPt = new Point(virtualX, virtualY);
        
        int temp = virtualPt.x;
        virtualPt.x = Math.round(virtualPt.x * cos - virtualPt.y * sin);
        virtualPt.y = Math.round(temp * sin + virtualPt.y * cos);
        
        return virtualPt;
    }
    
    public Point rotate(Point virtualPt, float angle) {
        return rotate(virtualPt.x, virtualPt.y, angle);
    }
}
