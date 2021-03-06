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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.Timer;
import shapes2d.Ellipse;
import shapes2d.Line;

/**
 *
 * @author COMPUTER
 */
public class Animation3 extends Animation {
    private Drop d[] = new Drop[120];
    
    public Animation3(JPanel jPanel, Graphics2D g2d) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super(jPanel, g2d);
        random = new Random();
        line = new Line();
        ellipse = new Ellipse();
        
        for (int i = 0; i < d.length; i++) {
            d[i] = new Drop();
        }
        
//        File file = new File("src/audio/Pachelbel Canon In D With Rain Sounds.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/audio/Pachelbel Canon In D With Rain Sounds.wav"));
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerActionPerformed(e);
            }
        });
        timer.start();
    }
    
    public void timerActionPerformed(ActionEvent e) {
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, maxX1, maxY1);
        for (int i = 0; i < d.length; i++) {
            if (d[i].virtualY < d[i].endPosition) {
                d[i].touchGround();
            } else {
                d[i].display();
            }
        }
        jPanel.repaint();
    }
    
    public class Drop {
        public int virtualX, virtualY;
        public float virtualYSpeed;
        public int ellipseX, ellipseY, endPosition;
        public int width, length;
        public Drop() {
            init();
        }
        public void init() {
            virtualX = random.nextInt(90 * 2) - 90;
            virtualY = random.nextInt(80) + 80;
            virtualYSpeed = random.nextInt(8) + 1;
            length = random.nextInt(6) + 2;
            width = random.nextInt(5) + 1;
            ellipseX = 0;
            ellipseY = 0;
            endPosition = MainFrame.getVirtualY(MainFrame.maxY1) + random.nextInt(40);
        }
        public void update() {
            virtualY -= virtualYSpeed;
            virtualYSpeed += 0.1f;
        }
        public void display() {
            g2d.setColor(Color.WHITE);
            line.draw(virtualX, virtualY, virtualX, virtualY + length, width, g2d);
            update();
        }
        public void touchGround() {
            ellipse.draw(virtualX, virtualY, ellipseX, ellipseY, g2d);
            ellipseX += virtualYSpeed / 2;
            ellipseY += virtualYSpeed / 2 - 1;
            if (ellipseX > 9) {
                init();
            }
        }
    }
}
