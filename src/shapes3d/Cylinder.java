/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes3d;

import java.awt.Graphics2D;
import java.awt.Point;
import computergraphicstechniques.MainFrame;
import shapes2d.Ellipse;
import shapes2d.Line;
import shapes2d.UpperDashedEllipse;

/**
 *
 * @author COMPUTER
 */
public class Cylinder {
    private int offset = MainFrame.PIXEL_SIZE / 2;
    
    // note (virtualX, virtualY, virtualZ) is center point of bottom ellipse
    public void draw(int virtualX, int virtualY, int virtualZ, int virtualR, int virtualHeight, Graphics2D g3d) {
        // convert 3D cooridnate to 2D coordinate using cabinet projection
        Point virtualBottomCenterPt = MainFrame.cabinet(virtualX, virtualY, virtualZ);
        Point virtualTopCenterPt = new Point(virtualBottomCenterPt.x, virtualBottomCenterPt.y + virtualHeight);
        
        // draw top and bottom ellipse of cylinder
        int virtualA = virtualR;
        int virtualB = MainFrame.cabinetDepth(virtualR);
        new Ellipse().draw(virtualTopCenterPt, virtualA, virtualB, g3d);
        new UpperDashedEllipse().draw(virtualBottomCenterPt, virtualA, virtualB, g3d);
        
        // draw left and right line of cylinder
        new Line().draw(virtualBottomCenterPt.x - virtualA, virtualBottomCenterPt.y, virtualTopCenterPt.x - virtualA, virtualTopCenterPt.y, g3d);
        new Line().draw(virtualBottomCenterPt.x + virtualA, virtualBottomCenterPt.y, virtualTopCenterPt.x + virtualA, virtualTopCenterPt.y, g3d);
    }
}
