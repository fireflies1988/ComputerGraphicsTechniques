/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import ktdhcuoiky.MainFrame;
import static ktdhcuoiky.MainFrame.maxX;
import static ktdhcuoiky.MainFrame.maxY;
import shapes2d.HinhChuNhat;

/**
 *
 * @author COMPUTER
 */
public class Animation1 {
    public Timer timer;
    private JPanel jPanel;
    private Graphics2D g2d;
    private HinhChuNhat hcn;
    private float angle = 10.0f;
    private final Point TL = new Point(-40, 40);
    private final Point BR = new Point(40, -40);
    private Random random = new Random();
    ArrayList<Point> r, s;
    
    public Animation1(JPanel jPanel, Graphics2D g2d) {
        this.jPanel = jPanel;
        this.g2d = g2d;
        MainFrame.PIXEL_SIZE = 3;
        hcn = new HinhChuNhat();
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer1ActionPerformed(e);
            }
        });
        timer.start();
    }

    public void timer1ActionPerformed(ActionEvent e) {
//        g2d.setBackground(new Color(255, 255, 255, 0));
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, maxX, maxY);
        g2d.setColor(Color.GREEN);
        Point virtualTL = new Point(TL);
        Point virtualBR = new Point(BR);
        for (int i = 1; i < 24; i++) {
//            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            r = hcn.rotate(virtualTL, virtualBR, i * angle);
            hcn.draw(r.get(0), r.get(1), r.get(2), r.get(3), g2d);
            s = hcn.scale(virtualTL, virtualBR, 0.92f, 0.92f);
            System.out.println(s);
            virtualTL = s.get(0);
            virtualBR = s.get(1);
        }
        jPanel.repaint();
        angle += 0.5;
    }
}
