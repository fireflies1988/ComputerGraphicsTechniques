/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import shapes2d.HinhChuNhat;

/**
 *
 * @author COMPUTER
 */
public class Animation1 implements ActionListener {
    private Timer timer1, timer2;
    private JPanel jPanel;
    private Graphics2D g2d;
    private HinhChuNhat hcn;
    private int angle = 0;
    Point virtualTL = new Point(-40, 40);
    Point virtualTR = new Point(-40, 40);
    
    public Animation1(JPanel jPanel, Graphics2D g2d) {
        this.jPanel = jPanel;
        this.g2d = g2d;
        hcn = new HinhChuNhat();
        timer1 = new Timer(100, this);
        timer1.start();
        timer2 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {
                    ArrayList<Point> r = hcn.rotate(new Point(-40, 40), new Point(40, -40), i * angle);
                    hcn.draw(r.get(0), r.get(1), r.get(2), r.get(3), g2d);
                    jPanel.repaint();
                }
                timer2.stop();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        g2d.setBackground(new Color(255, 255, 255, 0));
//        g2d.clearRect(0, 0, maxX, maxY);
//        timer2.start();
        ArrayList<Point> r = hcn.rotate(new Point(-40, 40), new Point(40, -40), angle);
        hcn.draw(r.get(0), r.get(1), r.get(2), r.get(3), g2d);
        jPanel.repaint();
        angle += 5;
    }
}
