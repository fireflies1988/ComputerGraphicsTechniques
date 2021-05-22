/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes3d;

import java.awt.Graphics2D;
import java.awt.Point;
import ktdhcuoiky.MainFrame;
import shapes2d.Circle;
import shapes2d.Ellipse;

/**
 *
 * @author COMPUTER
 */
public class Sphere {
    private int offset = MainFrame.PIXEL_SIZE / 2;
    
    public void draw(int virtualX, int virtualY, int virtualZ, int virtualR, Graphics2D g3d) {
        // convert 3D cooridnate to 2D coordinate using cabinet projection
        Point virtualPt = MainFrame.cabinet(virtualX, virtualY, virtualZ);
        
        // Circle
        Circle circle = new Circle();
        circle.draw(virtualPt, virtualR, g3d);
        
        // Ellipse inside circle
        int virtualA = virtualR;
        int virtualB = MainFrame.cabinetDepth(virtualR);
        Ellipse ellipse = new Ellipse();
        ellipse.draw(virtualPt, virtualA, virtualB, g3d);
    }
}
