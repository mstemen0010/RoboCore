/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AnimePanel.java
 *
 * Created on Jan 22, 2010, 1:31:59 AM
 */
package com.wintermute.bot.anime;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author mstemen
 */
public class AnimePanel extends java.awt.Panel implements Runnable
{

    private static final long serialVersionUID = -4135077494816536471L;
    Graphics mainGr = null;
    ImageIcon sleepImage = null;
    ImageIcon baseImage = null;
    ImageIcon jawImage = null;
    ImageIcon bgImage = null;
    int posX = 0;
    int posY = 0;
    boolean running = false;
    EmoteSequence currentEmote = EmoteSequence.Sleep;

    @Override
    public void run()
    {
        while (running) {
            try {
                Thread.sleep(5000);
                // EmotePhase.getNext();
                repaint();
            }
            catch (InterruptedException ex) {
                Logger.getLogger(AnimePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static int randGen(int range, int rangeDiv)
    {

        Random rand = new Random();
        long sysSeed = System.nanoTime();
        rand.setSeed(sysSeed);
        int nextRand = rand.nextInt();
        int nextNum = nextRand % range;
        if (nextNum < 1) {
            nextNum = nextNum + range;
        }
        int randPhaseInt = (int) nextNum / rangeDiv;
        System.out.println("AnimePanel::randGen: Next rand is: " + nextNum);


        return randPhaseInt;

    }

    private enum EmotePhase
    {

        Zero,
        First,
        Second,
        Third,
        Fourth,
        Fifth;
        private static EmotePhase current = First;

        public static EmotePhase getPhase()
        {
            return current;
        }

        public static void randNext()
        {
            int range = 1000;
            int rangeDiv = 200;


            int randPhaseInt = randGen(range, rangeDiv);
//            System.out.println("AnimePanel::EmotePhase::randNext: Next rand is: " + randPhaseInt);

            switch (randPhaseInt) {
            case 0:
                current = EmotePhase.Zero;
                break;
            case 1:
                current = EmotePhase.First;
                break;
            case 2:
                current = EmotePhase.Second;
                break;
            case 3:
                current = EmotePhase.Third;
                break;
            case 4:
                current = EmotePhase.Fourth;
                break;
            case 5:
                current = EmotePhase.Fifth;
                break;
            default:
                current = EmotePhase.Zero;
                break;
            }

            System.out.println("AnimePanel::EmotePhase::randNext: Phase is now: " + current.toString());
        }

        public static EmotePhase setNext()
        {
            switch (current) {
            case Zero:
                current = First;
            case First:
                current = Second;
                break;
            case Second:
                current = Third;
                break;
            case Third:
                current = Fourth;
                break;
            case Fourth:
                current = Fifth;
                break;
            case Fifth:
                current = Zero;
                break;

            }

            return current;
        }
    }

    /**
     *
     */
    public enum EmoteSequence
    {

        /**
         *
         */
        Sleep,
        /**
         *
         */
        Wake,
        /**
         *
         */
        Talk,
        /**
         *
         */
        Idle,
        /**
         *
         */
        Smile,
        /**
         *
         */
        Confused,
        /**
         *
         */
        Question,
        /**
         *
         */
        Mad,
        /**
         *
         */
        Angry,
        /**
         *
         */
        Laugh,
        /**
         *
         */
        BigSmile;
    }

    /** Creates new form AnimePanel
     * @param g
     * @param x
     * @param y
     */
    public AnimePanel(Graphics g, int x, int y)
    {
        mainGr = g;
        this.posX = x;
        this.posY = y;

        initComponents();
        initCanvas(g, x, y);
    }

    @Override
    public void paint(Graphics g)
    {
        System.out.println("AnimePanel::update: Painting animation, at pos: " + posX + ", " + posY);
        Image im = sleepImage.getImage();
//        mainGr.drawImage(im, posX, posY, this);
        drawFace(currentEmote, g);
    }

    @Override
    public void repaint()
    {
        paint(mainGr);
    }

    @Override
    public void update(Graphics g)
    {

        System.out.println("AnimePanel::update: Updating animation, at pos: " + posX + ", " + posY);
        Image im = sleepImage.getImage();
        mainGr.drawImage(im, posX, posY, this);

    }

    private void drawFace(EmoteSequence seq, Graphics g)
    {            
        System.out.println("AnimePanel::drawFace: Sequence is: " + seq.toString() + " Phase is: " + EmotePhase.getPhase());
        Image im = null;
        // EmotePhase.setNext();
        switch (seq) {
        case Sleep:
            im = sleepImage.getImage();
            mainGr.drawImage(im, posX, posY, this);
            break;
        default:
            EmotePhase.randNext();
            im = baseImage.getImage();
            mainGr.drawImage(im, posX, posY, this);

            drawEyes(seq, g);
            drawMouth(seq, g);
            break;
        }

    }

    /**
     *
     * @param emote
     */
    public void setEmote(EmoteSequence emote)
    {
        currentEmote = emote;
    }

    private void drawMouth(EmoteSequence seq, Graphics g)
    {
        int mouthX = posX + 31;
        int mouthY = posY + 137;
        int mouthW = 15;
        int mouthL = 39;
        Image im = jawImage.getImage();
        int mouthYMod = 0;


        switch(seq)
        {
        case Idle:
            mouthYMod = AnimePanel.randGen(100, 20);
            break;

        }
        // draw lower jaw
        g.drawImage(im, mouthX, mouthY + mouthYMod, this);
        // draw mouth "hole"
        g.setColor(Color.black);
        // g.setXORMode(Color.LIGHT_GRAY);
        // g.fillRect(mouthX, mouthY, mouthL, mouthW);
        // draw lower jaw
        g.setColor(Color.darkGray.brighter());
        // g.fillRoundRect(mouthX, mouthY + 7, mouthL, mouthW + 10, 4, 2);

        g.setPaintMode();
    }

    private void drawEyes(EmoteSequence seq, Graphics g)
    {
        int pupilSize = 10;
        int eyeSize = 20;
        int outerLeftEyeX = posX + 20;
        int outerLeftEyeY = posY + 96;
        int outerRightEyeX = outerLeftEyeX + 39;
        int outerRightEyeY = outerLeftEyeY;

        int lEyeX = posX + 25;
        int lEyeY = posY + 102;
        int rEyeX = lEyeX + 38;
        int rEyeY = lEyeY;
        int eyeSeparation = rEyeX - lEyeX;

        // eye positions
        int eyesRight = lEyeX + 3;
        int eyesCenter = lEyeX;
        int eyesLeft = lEyeX - 3;

        // draw outer eye
        g.setColor(Color.black);
        g.drawOval(outerLeftEyeX, outerLeftEyeY, eyeSize, eyeSize);
        g.drawOval(outerRightEyeX, outerRightEyeY, eyeSize, eyeSize);
//        g.setColor(Color.blue);

        EmotePhase phase = EmotePhase.getPhase();
        switch (seq) {
        case Sleep:
            break;
        case Wake:
            break;
        case Idle:
            switch (phase) {
            case Zero:
            case First:
                g.fillOval(eyesLeft, lEyeY, pupilSize, pupilSize);
                g.fillOval(eyesLeft + eyeSeparation, rEyeY, pupilSize, pupilSize);
                break;
            case Second:
            case Third:
                g.fillOval(eyesCenter, lEyeY, pupilSize, pupilSize);
                g.fillOval(eyesCenter + eyeSeparation, rEyeY, pupilSize, pupilSize);
                break;
            case Fourth:
            case Fifth:
                g.fillOval(eyesRight, lEyeY, pupilSize, pupilSize);
                g.fillOval(eyesRight + eyeSeparation, rEyeY, pupilSize, pupilSize);
                break;
            }

            break;

        }
    }

//    @Override
//    public void repaint()
//    {
//
//    }
    private void initCanvas(Graphics g, int x, int y)
    {

        running = true;
        sleepImage = new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/robbieFace_sleep.png"));
        baseImage = new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/robbieFace_Base.png"));
        jawImage = new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/robbieJaw.png"));
        Image im = sleepImage.getImage();
        // Graphics g = this.animeCanvas.getGraphics();
        g.drawImage(im, posX, posY, this);


    }

    /**
     *
     */
    public void stopCanvas()
    {
        running = false;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sleepImageLabel = new javax.swing.JLabel();
        animeCanvas = new java.awt.Canvas();

        sleepImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/robbieFace_sleep.png"))); // NOI18N
        sleepImageLabel.setMaximumSize(new java.awt.Dimension(100, 200));
        sleepImageLabel.setMinimumSize(new java.awt.Dimension(100, 200));
        sleepImageLabel.setPreferredSize(new java.awt.Dimension(100, 200));

        setLayout(new java.awt.GridLayout());

        animeCanvas.setBackground(new java.awt.Color(0, 0, 0));
        add(animeCanvas);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas animeCanvas;
    private javax.swing.JLabel sleepImageLabel;
    // End of variables declaration//GEN-END:variables
}
