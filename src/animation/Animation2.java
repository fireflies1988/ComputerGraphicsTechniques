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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.Timer;
import ktdhcuoiky.MainFrame;
import static ktdhcuoiky.MainFrame.maxX1;
import static ktdhcuoiky.MainFrame.maxY1;
import shapes2d.Circle;
import shapes2d.Line;

/**
 *
 * @author COMPUTER
 */
public class Animation2 {
    private final Point pt1 = new Point(45, 0);
    private final Point pt2 = new Point(0, 45);
    private JPanel jPanel;
    private Graphics2D g2d;
    public Timer timer;
    private float angle = 0.0f;
    private Line line;
    private Circle circle;
    private ArrayList<Point> rl;
    private Point rc1, rc2;
    public Clip clip;
    
    public Animation2(JPanel jPanel, Graphics2D g2d) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.jPanel = jPanel;
        this.g2d = g2d;
        MainFrame.PIXEL_SIZE = 3;
        
        File file = new File("src/audio/Invisible - Vibe Tracks.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        line = new Line();
        circle = new Circle();
        
        timer = new Timer(1, new ActionListener() {
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
        g2d.setColor(Color.GREEN);
        float sin = (float) (Math.sin(Math.toRadians(angle)));
        for (float i = 0; i < 360; i += 10) {

//            if (angle < 360) {
//                rl = line.rotate(Math.round(pt1.x * sin), 0, pt2.x, 0, i);
//                System.out.println(rl);
//                line.draw(rl.get(0).x, rl.get(0).y, rl.get(1).x, rl.get(1).y, g2d);
//            } else if (angle < 360 * 2) {
//                rl = line.rotate(Math.round(pt1.x * sin), 0, 45, pt2.y, i);
//                System.out.println(rl);
//                line.draw(rl.get(0).x, rl.get(0).y, rl.get(1).x, rl.get(1).y, g2d);
//            } else if (angle < 360 * 3) {
//                rl = line.rotate(Math.round(pt1.x * sin), 0, pt2.x, pt2.y, i);
//                System.out.println(rl);
//                line.draw(rl.get(0).x, rl.get(0).y, rl.get(1).x, rl.get(1).y, g2d);
//            }
//            else 
            if (angle < 360 * 2) {
                rl = line.rotate(Math.round(pt1.x * sin), pt1.y, pt2.x, pt2.y, i * sin);
                rl = line.rotate(rl.get(0), rl.get(1), angle);
                line.draw(rl.get(0), rl.get(1), g2d);

                rc1 = circle.rotate(Math.round(pt1.x * sin), pt1.y, i * sin);
                rc1 = circle.rotate(rc1, angle);
                circle.draw(rc1, 1, g2d);

                rc2 = circle.rotate(pt2.x, pt2.y, i * sin);
                rc2 = circle.rotate(rc2, angle);
                circle.draw(rc2, 2, g2d);
            } else if (angle < 360 * 4) {
                rl = line.rotate(Math.round(pt1.x * sin), pt1.y, pt2.x, pt2.y, i);
                rl = line.rotate(rl.get(0), rl.get(1), angle);
                line.draw(rl.get(0), rl.get(1), g2d);

                rc1 = circle.rotate(Math.round(pt1.x * sin), pt1.y, i);
                rc1 = circle.rotate(rc1, angle);
                circle.draw(rc1, 1, g2d);

                rc2 = circle.rotate(0, pt2.y, i);
                rc2 = circle.rotate(rc2, angle);
                circle.draw(rc2, 2, g2d);
            } else {
                angle = 0;
            }
        }
        angle++;
        jPanel.repaint();
    }
}
