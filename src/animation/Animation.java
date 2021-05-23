/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;
import shapes2d.Circle;
import shapes2d.Ellipse;
import shapes2d.Line;
import shapes2d.Point2D;
import shapes2d.Rectangle;

/**
 *
 * @author COMPUTER
 */
public abstract class Animation {
    protected JPanel jPanel;
    protected Graphics2D g2d;
    protected Random random;
    protected Rectangle rect;
    protected Circle circle;
    protected Ellipse ellipse;
    protected Line line;
    protected Point2D point;
    public Timer timer;
    public Clip clip;
    
    public Animation(JPanel jPanel, Graphics2D g2d) {
        this.jPanel = jPanel;
        this.g2d = g2d;
    }
    
    public abstract void timerActionPerformed(ActionEvent e);
}
