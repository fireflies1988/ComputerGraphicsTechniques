/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes2d;

import computergraphicstechniques.MainFrame;
import java.awt.Graphics2D;

/**
 *
 * @author COMPUTER
 */
public class Point2D extends Shape{
    private int virtualX, virtualY;

    public Point2D() {
    }
    
    public Point2D(int virtualX, int virtualY) {
        this.virtualX = virtualX;
        this.virtualY = virtualY;
    }
    
    public void draw(Graphics2D g2d) {
        int realX = MainFrame.getRealX(virtualX);
        int realY = MainFrame.getRealY(virtualY);
        
        g2d.fillRect(realX - offset, realY - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
    }
    
    public void draw(int virtualX, int virtualY, Graphics2D g2d) {
        int realX = MainFrame.getRealX(virtualX);
        int realY = MainFrame.getRealY(virtualY);
        
        g2d.fillRect(realX - offset, realY - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
    }
}
