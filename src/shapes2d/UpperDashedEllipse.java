/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes2d;

import java.awt.Graphics2D;
import java.awt.Point;
import computergraphicstechniques.MainFrame;

/**
 *
 * @author COMPUTER
 */
public class UpperDashedEllipse extends Shape {
    private int counter = 0;
    
    public void put2PixelsUpperHalf(int x, int y, int xE, int yE, Graphics2D g2d) {
        // goc phan tu IV
        g2d.fillRect(x + xE - offset, -y + yE - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        // goc phan tu III
        g2d.fillRect(-x + xE - offset, -y + yE - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
    }
    
    public void put2PixelsLowerHalf(int x, int y, int xE, int yE, Graphics2D g2d) {
        // goc phan tu I
        g2d.fillRect(x + xE - offset, y + yE - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
        // goc phan tu II
        g2d.fillRect(-x + xE - offset, y + yE - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);
    }
    
    public void draw(int virtualXE, int virtualYE, int virtualA, int virtualB, Graphics2D g2d) {
        // Convert virtualX, virtualY to realX, realY
        int xE = MainFrame.getRealX(virtualXE);
        int yE = MainFrame.getRealY(virtualYE);
        int a = MainFrame.getRealWH(virtualA);
        int b = MainFrame.getRealWH(virtualB);
        
        // Midpoint ve ellipse
        int x, y, fx, fy, a2, b2, p;
        x = 0;
        y = b;
        a2 = a * a;
        b2 = b * b;

        // dao ham rieng cua f(x, y) = b2 * x * x + a2 * y * y - a2 * b2
        fx = 0; // fx = 2 * b2 * x, x = 0 => fx = 0
        fy = 2 * a2 * y; // fy = 2 * a2 * y
        
        put2PixelsUpperHalf(x, y, xE, yE, g2d);
        put2PixelsLowerHalf(x, y, xE, yE, g2d);
        
        // do doc tiep tuyen ellipse tai (x, y) bat ky: m = dy/dx = -fy/fx (diem nay phan chia 2 phan ellipse de ve)
        // ve phan I: m < -1 => fx < fy
        p = Math.round(b2 - a2 * b + a2 / 4.0f);
        while (fx < fy) {
//            fx += 2 * b2; // fx' = 2 * b2 * (x + 1) = fx + 2 * b2
            x++;
            fx += 2 * b2; // fx' = 2 * b2 * (x + unit) = fx + 2 * unit * b2
            if (p < 0) {
                // midpoint nam trong duong ellipse y = y
                p += b2 * (2 * x + 3);
            } else {
                // midpoint nam ngoai duong ellipse y = y - 1;
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);
                fy -= 2 * a2; // fy' = 2 * a2 * (y + unit) = fy + 2 * unit * a2
            }
            if (x % MainFrame.UNIT == 0) {
                int tempY = MainFrame.roundXY(y);
                if (counter < 4) {
                    put2PixelsUpperHalf(x, tempY, xE, yE, g2d);
                    put2PixelsLowerHalf(x, tempY, xE, yE, g2d);
                    counter++;
                } else if (counter < 7) {
                    put2PixelsLowerHalf(x, tempY, xE, yE, g2d);
                    counter++;
                } else {
                    put2PixelsUpperHalf(x, tempY, xE, yE, g2d);
                    put2PixelsLowerHalf(x, tempY, xE, yE, g2d);
                    counter = 0;
                }
            }
        }
        
        // ve phan II
        p = Math.round(b2 * (x + 0.5f) * (x + 0.5f) + a2 * (y - 1) * (y - 1) - a2 * b2);
        while (y > 0) {
            y--;
            fy -= 2 * a2;  // What the hell is this used for?
            if (p >= 0) {
                // midpoint nam ngoai ellipse x = x
                p += a2 * (3 - 2 * y);
            } else {
                // midpoint nam trong ellipse x++
                x++;
                fx += 2 * b2;   // here too. It's redundant.
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);
            }   
            if (y % MainFrame.UNIT == 0) {
                int tempX = MainFrame.roundXY(x);
                if (counter < 4) {
                    put2PixelsUpperHalf(tempX, y, xE, yE, g2d);
                    put2PixelsLowerHalf(tempX, y, xE, yE, g2d);
                    counter++;
                } else if (counter < 7) {
                    put2PixelsLowerHalf(tempX, y, xE, yE, g2d);
                    counter++;
                } else {
                    put2PixelsUpperHalf(tempX, y, xE, yE, g2d);
                    put2PixelsLowerHalf(tempX, y, xE, yE, g2d);
                    counter = 0;
                }
            }
        }
    }
    
    public void draw(Point virtualPt, int virtualA, int virtualB, Graphics2D g2d) {
        draw(virtualPt.x, virtualPt.y, virtualA, virtualB, g2d);
    }
}
