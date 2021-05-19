/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes2d;

import java.awt.Graphics2D;
import ktdhcuoiky.MainFrame;

/**
 *
 * @author COMPUTER
 */
public class DoanThang {
    private int x1, y1, x2, y2;
    private int offset = MainFrame.PIXEL_SIZE / 2;

    public DoanThang() {
    }

    public DoanThang(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public void draw(int x1, int y1, int x2, int y2, Graphics2D g2d) {
        // Bresenham
        int Dx, Dy, p, x, y;
        Dx = Math.abs(x2 - x1);
        Dy = Math.abs(y2 - y1);
           
        x = x1;
        y = y1;

        int xUnit = MainFrame.PIXEL_SIZE, yUnit = MainFrame.PIXEL_SIZE;
        if (x1 > x2) {
            xUnit = -xUnit;
        }
        if (y1 > y2) {
            yUnit = -yUnit;
        }
        
        g2d.fillRect(x - offset, y - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);  
        if (Dx >= Dy) {
            p = 2 * Dy - Dx;
            while (x != x2) {
                if (p < 0) p += 2 * Dy;
                else {
                    p += 2 * (Dy - Dx);
                    y += yUnit;
                }
                x += xUnit;
                g2d.fillRect(x - offset, y - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);  
            }
        } else {
            p = 2 * Dx - Dy;
            while (y != y2) {
                if (p < 0) p += 2 * Dx;
                else {
                    p += 2 * (Dx - Dy);
                    x += xUnit;
                }
                y += yUnit;
                g2d.fillRect(x - offset, y - offset, MainFrame.PIXEL_SIZE, MainFrame.PIXEL_SIZE);  
            }
        }
    }
}
