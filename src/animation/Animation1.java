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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.Timer;
import computergraphicstechniques.MainFrame;
import shapes2d.Rectangle;
import static computergraphicstechniques.MainFrame.maxX1;
import static computergraphicstechniques.MainFrame.maxY1;

/**
 *
 * @author COMPUTER
 */
public class Animation1 {
    private final Point TL = new Point(-40, 40);
    private final Point BR = new Point(40, -40);
    private JPanel jPanel;
    private Graphics2D g2d;
    public Timer timer;
    private Rectangle rect;
    ArrayList<Point> r, s;
    private float angle = 10.0f;
    private Random random = new Random();
    public Clip clip;
    
    public Animation1(JPanel jPanel, Graphics2D g2d) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.jPanel = jPanel;
        this.g2d = g2d;
        MainFrame.PIXEL_SIZE = 3;
        
        File file = new File("src/audio/Universal - Vibe Tracks.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        rect = new Rectangle();
        
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerActionPerformed(e);
            }
        });
        timer.start();
    }

    public void timerActionPerformed(ActionEvent e) {
//        g2d.setBackground(new Color(255, 255, 255, 0));
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, maxX1, maxY1);
        g2d.setColor(Color.GREEN);
        Point virtualTL = new Point(TL);
        Point virtualBR = new Point(BR);
        for (int i = 1; i < 24; i++) {
//            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            r = rect.rotate(virtualTL, virtualBR, i * angle);
            rect.draw(r.get(0), r.get(1), r.get(2), r.get(3), g2d);
            s = rect.scale(virtualTL, virtualBR, 0.92f, 0.92f);
            System.out.println(s);
            virtualTL = s.get(0);
            virtualBR = s.get(1);
        }
        jPanel.repaint();
        angle += 0.5;
    }
}
