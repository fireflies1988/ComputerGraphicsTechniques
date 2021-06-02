/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import computergraphicstechniques.MainFrame;
import static computergraphicstechniques.MainFrame.maxX1;
import static computergraphicstechniques.MainFrame.maxY1;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import shapes2d.Rectangle;

/**
 *
 * @author COMPUTER
 */
public class Animation5 extends Animation {
    private FallingRect fallingRect[] = new FallingRect[35];
    private int endPosition = MainFrame.getVirtualY(maxY1);
    public Animation5(JPanel jPanel, Graphics2D g2d) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super(jPanel, g2d);
        rect = new Rectangle();
        random = new Random();
        for (int i = 0; i < fallingRect.length; i++) {
            fallingRect[i] = new FallingRect();
        }
//        MainFrame.PIXEL_SIZE = 3;
        
//        File file = new File("src/audio/Amadeus - Future world.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/audio/Amadeus - Future world.wav"));
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerActionPerformed(e);
            }
        });
        timer.start();
    }
    
    public void timerActionPerformed(ActionEvent e) {
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, maxX1, maxY1);
        for (int i = 0; i < fallingRect.length; i++) {
            fallingRect[i].display();
            if (fallingRect[i].virtualY < endPosition) {
                fallingRect[i] = new FallingRect();
            }
        }
        jPanel.repaint();
    }
    
    
    public class FallingRect {
        private int virtualX, virtualY;
        private float virtualYSpeed;
        private int virtualWidth, virtualHeight;
        private float shearX, shearY;
        private float angle;
        private Color c;
        private ArrayList<Point> r, t, sh;
        
        public FallingRect() {
            virtualX = random.nextInt(80 * 2) - 80;
            virtualY = random.nextInt(130) + 80;
            virtualYSpeed = random.nextFloat() * 2.0f + 1.0f;
            virtualWidth = random.nextInt(12) + 4;
            virtualHeight = random.nextInt(12) + 4;
            angle = random.nextInt(20) - 10;
            shearX = random.nextFloat() * 2.0f - 1.0f;
            shearY = random.nextFloat() * 2.0f - 1.0f;
            c = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }
        
        public void update() {
            if (angle > 0) {
                angle += 5;
            } else {
                angle -= 5;
            }
            virtualY -= Math.round(virtualYSpeed);
//            virtualYSpeed += 0.1f;
        }
        public void display() {
            g2d.setColor(c);
            
            // effect 0
//            Point center = rect.getCenterPoint(virtualX, virtualY, virtualWidth, virtualHeight);
//            t = rect.translate(virtualX, virtualY, virtualWidth, virtualHeight, -center.x, -center.y);
//            r = rect.rotate(t.get(0), t.get(1), angle);
//            t = rect.translate(r.get(0), r.get(1), r.get(2), r.get(3), center.x, center.y);
//            sh = rect.shear(t.get(0), t.get(1), t.get(2), t.get(3), shearX, shearY);
//            rect.draw(sh.get(0), sh.get(1), sh.get(2), sh.get(3), g2d);

            // effect 1
//            r = rect.rotate(virtualX, virtualY, virtualWidth, virtualHeight, angle);
//            sh = rect.shear(r.get(0), r.get(1), r.get(2), r.get(3), shearX, shearY);
//            rect.draw(sh.get(0), sh.get(1), sh.get(2), sh.get(3), g2d);

            // effect 2
//            t = rect.translate(virtualX, virtualY, virtualWidth, virtualHeight, -virtualX, -virtualY);
//            r = rect.rotate(t.get(0), t.get(1), angle);
//            sh = rect.shear(r.get(0), r.get(1), r.get(2), r.get(3), shearX, shearY);
//            t = rect.translate(sh.get(0), sh.get(1), sh.get(2), sh.get(3), virtualX, virtualY);
//            rect.draw(t.get(0), t.get(1), t.get(2), t.get(3), g2d);
            
            // effect 3
            t = rect.translate(virtualX, virtualY, virtualWidth, virtualHeight, -virtualX, -virtualY);
            r = rect.rotate(t.get(0), t.get(1), angle);
            t = rect.translate(r.get(0), r.get(1), r.get(2), r.get(3), virtualX, virtualY);
            sh = rect.shear(t.get(0), t.get(1), t.get(2), t.get(3), shearX, shearY);
            rect.draw(sh.get(0), sh.get(1), sh.get(2), sh.get(3), g2d);
            
            
            update();
        }
    }
}
