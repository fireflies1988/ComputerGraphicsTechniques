/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes2d;

import java.awt.Graphics;
import java.awt.Graphics2D;
import ktdhcuoiky.MainFrame;

/**
 *
 * @author COMPUTER
 */
public class HinhTron {
    private int x, y, r;
    private int offset = MainFrame.PIXEL_SIZE / 2;

    public HinhTron() {
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
    
    public void draw(int xC, int yC, int r, Graphics2D g2d) {
        // Midpoint
        int x = 0, y = r;
        int f = 1 - r; // f = 5/4 - r vi 5/4 = 1

        int count = 0;
        put8Pixels(x, y, xC, yC, g2d); // putpixel va tinh tien hinh tron tai (0, 0) sang (xC, yC) 

        while (x < y) {
            x += MainFrame.PIXEL_SIZE;
            if (f < 0) f += 2 * x + 3;
            else {
                f += 2 * (x - y) + 5;
                y -= MainFrame.PIXEL_SIZE;
            }
            put8Pixels(x, y, xC, yC, g2d);
        }
    }
}
