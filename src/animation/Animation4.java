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
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.Timer;
import shapes2d.Point2D;

/**
 *
 * @author COMPUTER
 */
public class Animation4 extends Animation {
    private int n = 1, d = 1;
    private float k;
    private float radian = 0;
    private int a = 50;
    
    
    public Animation4(JPanel jPanel, Graphics2D g2d) throws UnsupportedAudioFileException, IOException, LineUnavailableException, IOException {
        super(jPanel, g2d);
        point = new Point2D();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, maxX1, maxY1);
        
        File file = new File("src/audio/Foundation - Vibe Tracks.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerActionPerformed(e);
            }
        });
        timer.start();
    }
    
    // Rose (mathematics)
    // https://en.wikipedia.org/wiki/Rose_(mathematics) for more information
    public void timerActionPerformed(ActionEvent e) {
        g2d.setColor(Color.GREEN);
        k = (float) n / d;
        float r = a * (float) Math.cos(k * radian);
        int x = Math.round(r * (float) Math.cos(radian));
        int y = Math.round(r * (float) Math.sin(radian));
        point.draw(x, y, g2d);
        radian += 0.02f;
        System.out.println(radian);
        if (radian > Math.PI * 2 * d) {
            radian = 0;
            g2d.clearRect(0, 0, maxX1, maxY1);
            g2d.setColor(Color.WHITE);
            n++;
            if (n > 7) {
                n = 1;
                d++;
                if (d > 9) {
                    d = 1;
                }
            }
        }
        jPanel.repaint();
    }
}
