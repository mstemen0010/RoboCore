/*
 * RobbieMgr.java
 *
 * Created on September 16, 2008, 4:58 PM
 */
package com.wintermute.bot;

import com.wintermute.bot.anime.AnimePanel;
import com.wintermute.bot.anime.AnimePanel.EmoteSequence;
import com.wintermute.brain.cortex.BrainCortex;
import com.wintermute.brain.cortex.BrainCortexListener;
import com.wintermute.brain.frame.BrainFrameException;
import com.wintermute.brain.thoughtarray.AnswerObject;
import com.wintermute.brain.thoughtarray.ThoughtConsumer;
import com.wintermute.brain.thoughtarray.ThoughtObject;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;
import com.wintermute.nlp.structure.Phrase;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  mstemen
 */
public class BotViewer extends javax.swing.JFrame implements ThoughtConsumer, BotViewInterface, BotListener, BrainCortexListener
{

    private static final long serialVersionUID = 4374809649087705242L;
    private ThoughtObject questionObject;
    private AnswerObject answerObject;
    private RobbieBot myBot = null;
    private AnimePanel robbieAnime = null;
    private int robbiePosX = 3;
    private int robbiePosY = 335;
    private Thread robbieAnimeThread = null;
    private StringBuilder answer = new StringBuilder();
    private Phrase answerPhrase = null;
    private BrainCortex myBrainCortex = null;

    /**
     *
     */
    @Override
    public void indicateQuestion()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void process(ThoughtObject to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    enum BotViewColor
    {

        Font1,
        Font2,
        Frame,
        FrameTrim,
        Panel,
        PanelTrim,
        SubConArea,
        ConArea;
        static Color fontColor = Color.BLACK;
        static Color frameColor = Color.BLACK;
        static Color panelColor = new Color(204, 51, 0); // med orange
        static Color textArea = Color.YELLOW;

        public static Color getColor(BotViewColor fromColor)
        {
            Color retColor = null;
            switch (fromColor) {
            case Font1:
            case Font2:
                retColor = fontColor;
                break;
            case Frame:
                retColor = frameColor;
                break;
            case FrameTrim:
                retColor = frameColor.brighter().brighter();
                break;
            case Panel:
                retColor = panelColor;
                break;
            case PanelTrim:
                retColor = panelColor.brighter();
                break;
            case ConArea:
                retColor = textArea.brighter();
                break;
            case SubConArea:
                retColor = textArea.darker().darker();
                break;


            }

            return retColor;
        }
    }

    /**
     *
     */
    public void setupRobbieAnime()
    {
        robbieAnime = new AnimePanel(this.getGraphics(), robbiePosX, robbiePosY);
        robbieAnime.setEmote(EmoteSequence.Sleep);
        robbieAnimeThread = new Thread(robbieAnime);

        RobbieExpressionPanel.removeAll();
        RobbieExpressionPanel.setPreferredSize(new Dimension(100,100));
        RobbieExpressionPanel.add(robbieAnime);
        robbieAnimeThread.start();


    }

    /**
     *
     * @param flag
     */
    public void indicationQuestion(boolean flag)
    {
        if( flag )
            this.questionIcon.setText("?");
        else
            this.questionIcon.setText("");
        
    }
  
    @Override
    public void update(Observable arg0, Object arg1)
    {
        // arg should be a ThoughtObject
        StringBuilder sb = new StringBuilder("BotViewer::update: ");
        Object o = arg1;
        ThoughtObject to;


        if (ThoughtObject.class.isInstance(o)) {
            to = ThoughtObject.class.cast(o);
            if (to == null) {
                sb.append("ThoughtObject was null! The Source was: ").append(arg0.toString()).append("\n");
                System.out.println(sb.toString());
                return;
            }
            else {
                sb.append("Got new ThoughtObject: " + to.toString());
                System.out.println(sb.toString());
            }

            ThoughtType typ = to.getType();
            ModiferType mod = to.getModifer();

            if (typ == ThoughtType.Question || typ == ThoughtType.Statement) {
                robbieDiaOut.append(to.getRawThought());
                robbieDiaOut.append("\n");
            }
            else if (to.getModifer() == ModiferType.Indicate) {
                showThought(to);
            }
            else {
                say(to);
            }
        }
    }

    /** Creates new form RobbieMgr */
    @SuppressWarnings("static-access")
    public BotViewer()
    {
        initComponents();
        this.questionIcon.setText("");
    }

    /**
     *
     * @param question
     */
    public void ask(String question)
    {
        this.parsedAnswer.setText(question);
    }

    /**
     *
     */
    public void answer()
    {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        offLed = new javax.swing.JLabel();
        greenLed = new javax.swing.JLabel();
        redLed = new javax.swing.JLabel();
        blueLed = new javax.swing.JLabel();
        yellowLed = new javax.swing.JLabel();
        purpleLed = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        RobbieOut = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        RobbieControlPanel = new javax.swing.JPanel();
        robbieStartButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        robbieIdValue = new javax.swing.JLabel();
        robbieStatusIcon = new javax.swing.JLabel();
        indicationPanel = new javax.swing.JPanel();
        aliveIndicator = new javax.swing.JLabel();
        subConIndicator = new javax.swing.JLabel();
        thinkIndicator = new javax.swing.JLabel();
        learnIndicator = new javax.swing.JLabel();
        loopIndicator = new javax.swing.JLabel();
        conIndicator = new javax.swing.JLabel();
        questionIcon = new javax.swing.JLabel();
        jSplitPane3 = new javax.swing.JSplitPane();
        conscienencePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        consciousOut = new javax.swing.JTextArea();
        subConsciencePanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        subConsciousOut = new javax.swing.JTextArea();
        DialogPanel = new javax.swing.JPanel();
        RobbieExpressionPanel = new javax.swing.JPanel();
        RobbieDiaPanel = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        RobbieDiaOut = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        robbieDiaOut = new javax.swing.JTextArea();
        RobbieDiaIn = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        parsedAnswer = new javax.swing.JLabel();
        userAnswer = new javax.swing.JTextField();
        userAnswerButton = new javax.swing.JButton();

        offLed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/off_led.jpg"))); // NOI18N
        offLed.setText("jLabel5");

        greenLed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/green_led.jpg"))); // NOI18N
        greenLed.setText("jLabel5");

        redLed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/red_led.jpg"))); // NOI18N
        redLed.setText("jLabel5");

        blueLed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/blue_led.jpg"))); // NOI18N
        blueLed.setText("jLabel2");

        yellowLed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/yellow_led.jpg"))); // NOI18N
        yellowLed.setText("jLabel2");

        purpleLed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/purple_led.jpg"))); // NOI18N
        purpleLed.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RoboCore 4.2 - Robbie Bot and Brain");
        setBackground(BotViewColor.getColor(BotViewColor.Frame));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(700, 600));
        jSplitPane1.setPreferredSize(new java.awt.Dimension(850, 600));

        RobbieOut.setPreferredSize(new java.awt.Dimension(700, 400));
        RobbieOut.setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane2.setDividerLocation(75);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        RobbieControlPanel.setBackground(BotViewColor.getColor(BotViewColor.Panel));
        RobbieControlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        RobbieControlPanel.setPreferredSize(new java.awt.Dimension(700, 100));

        robbieStartButton.setBackground(BotViewColor.getColor(BotViewColor.Panel));
        robbieStartButton.setText("Start");
        robbieStartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                robbieStartButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Robbie ID:");

        robbieIdValue.setBackground(new java.awt.Color(204, 51, 0));
        robbieIdValue.setText("Unknown");

        robbieStatusIcon.setBackground(BotViewColor.getColor(BotViewColor.Panel));
        robbieStatusIcon.setForeground(BotViewColor.getColor(BotViewColor.Font1));
        robbieStatusIcon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        robbieStatusIcon.setIcon(offLed.getIcon());
        robbieStatusIcon.setText("Status:");
        robbieStatusIcon.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        indicationPanel.setBackground(BotViewColor.getColor(BotViewColor.FrameTrim    ));

        aliveIndicator.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12));
        aliveIndicator.setForeground(new java.awt.Color(255, 255, 255));
        aliveIndicator.setIcon(offLed.getIcon());
        aliveIndicator.setText("Alive");
        aliveIndicator.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        aliveIndicator.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/off_led.jpg"))); // NOI18N
        aliveIndicator.setEnabled(false);
        aliveIndicator.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        aliveIndicator.setIconTextGap(10);

        subConIndicator.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12));
        subConIndicator.setForeground(new java.awt.Color(255, 255, 255));
        subConIndicator.setIcon(blueLed.getIcon());
        subConIndicator.setText("Subco");
        subConIndicator.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        subConIndicator.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/off_led.jpg"))); // NOI18N
        subConIndicator.setEnabled(false);
        subConIndicator.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        subConIndicator.setIconTextGap(10);

        thinkIndicator.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        thinkIndicator.setForeground(new java.awt.Color(255, 255, 255));
        thinkIndicator.setIcon(blueLed.getIcon());
        thinkIndicator.setText(" Thoug");
        thinkIndicator.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        thinkIndicator.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/off_led.jpg"))); // NOI18N
        thinkIndicator.setEnabled(false);
        thinkIndicator.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        thinkIndicator.setIconTextGap(10);

        learnIndicator.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12));
        learnIndicator.setForeground(new java.awt.Color(255, 255, 255));
        learnIndicator.setIcon(blueLed.getIcon());
        learnIndicator.setText(" Learn");
        learnIndicator.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        learnIndicator.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/off_led.jpg"))); // NOI18N
        learnIndicator.setEnabled(false);
        learnIndicator.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        learnIndicator.setIconTextGap(10);

        loopIndicator.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        loopIndicator.setForeground(new java.awt.Color(255, 255, 255));
        loopIndicator.setIcon(blueLed.getIcon());
        loopIndicator.setText("Loop");
        loopIndicator.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        loopIndicator.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/off_led.jpg"))); // NOI18N
        loopIndicator.setEnabled(false);
        loopIndicator.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        loopIndicator.setIconTextGap(10);

        conIndicator.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12));
        conIndicator.setForeground(new java.awt.Color(255, 255, 255));
        conIndicator.setIcon(blueLed.getIcon());
        conIndicator.setText("Consc");
        conIndicator.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        conIndicator.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wintermute/images/off_led.jpg"))); // NOI18N
        conIndicator.setEnabled(false);
        conIndicator.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        conIndicator.setIconTextGap(10);

        questionIcon.setBackground(new java.awt.Color(51, 51, 51));
        questionIcon.setFont(new java.awt.Font("Xirod", 0, 48));
        questionIcon.setForeground(new java.awt.Color(204, 102, 0));
        questionIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionIcon.setText("?");
        questionIcon.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204), null, null));
        questionIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        questionIcon.setIconTextGap(0);
        questionIcon.setMaximumSize(new java.awt.Dimension(360, 660));
        questionIcon.setPreferredSize(new java.awt.Dimension(115, 48));

        org.jdesktop.layout.GroupLayout indicationPanelLayout = new org.jdesktop.layout.GroupLayout(indicationPanel);
        indicationPanel.setLayout(indicationPanelLayout);
        indicationPanelLayout.setHorizontalGroup(
            indicationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(indicationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(indicationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(indicationPanelLayout.createSequentialGroup()
                        .add(loopIndicator)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(subConIndicator))
                    .add(indicationPanelLayout.createSequentialGroup()
                        .add(aliveIndicator)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(conIndicator)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(indicationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(thinkIndicator)
                    .add(learnIndicator))
                .add(28, 28, 28)
                .add(questionIcon, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10))
        );
        indicationPanelLayout.setVerticalGroup(
            indicationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(indicationPanelLayout.createSequentialGroup()
                .add(10, 10, 10)
                .add(indicationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(indicationPanelLayout.createSequentialGroup()
                        .add(indicationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(aliveIndicator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(thinkIndicator)
                            .add(conIndicator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(indicationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(loopIndicator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(learnIndicator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(subConIndicator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(questionIcon, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout RobbieControlPanelLayout = new org.jdesktop.layout.GroupLayout(RobbieControlPanel);
        RobbieControlPanel.setLayout(RobbieControlPanelLayout);
        RobbieControlPanelLayout.setHorizontalGroup(
            RobbieControlPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RobbieControlPanelLayout.createSequentialGroup()
                .add(RobbieControlPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel1)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, robbieStartButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(RobbieControlPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(robbieStatusIcon)
                    .add(robbieIdValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                .add(144, 144, 144)
                .add(indicationPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        RobbieControlPanelLayout.setVerticalGroup(
            RobbieControlPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RobbieControlPanelLayout.createSequentialGroup()
                .add(RobbieControlPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(RobbieControlPanelLayout.createSequentialGroup()
                        .add(RobbieControlPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(robbieStartButton)
                            .add(robbieStatusIcon))
                        .add(1, 1, 1)
                        .add(RobbieControlPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(robbieIdValue)))
                    .add(indicationPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(RobbieControlPanel);

        jSplitPane3.setDividerLocation(500);

        conscienencePanel.setBackground(BotViewColor.getColor(BotViewColor.Panel));
        conscienencePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conscious", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        conscienencePanel.setPreferredSize(new java.awt.Dimension(300, 261));
        conscienencePanel.setLayout(new java.awt.GridLayout(1, 0));

        consciousOut.setBackground(BotViewColor.getColor(BotViewColor.ConArea));
        consciousOut.setColumns(20);
        consciousOut.setFont(new java.awt.Font("Lucida Grande", 0, 8));
        consciousOut.setRows(8);
        jScrollPane2.setViewportView(consciousOut);

        conscienencePanel.add(jScrollPane2);

        jSplitPane3.setLeftComponent(conscienencePanel);

        subConsciencePanel.setBackground(BotViewColor.getColor(BotViewColor.Panel));
        subConsciencePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Subconscious", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        subConsciencePanel.setPreferredSize(new java.awt.Dimension(300, 261));
        subConsciencePanel.setLayout(new java.awt.GridLayout(1, 0));

        subConsciousOut.setBackground(new java.awt.Color(0, 0, 0));
        subConsciousOut.setColumns(20);
        subConsciousOut.setFont(new java.awt.Font("Lucida Grande", 0, 8));
        subConsciousOut.setForeground(new java.awt.Color(255, 255, 255));
        subConsciousOut.setRows(8);
        jScrollPane3.setViewportView(subConsciousOut);

        subConsciencePanel.add(jScrollPane3);

        jSplitPane3.setRightComponent(subConsciencePanel);

        jSplitPane2.setBottomComponent(jSplitPane3);

        RobbieOut.add(jSplitPane2);

        jSplitPane1.setLeftComponent(RobbieOut);

        DialogPanel.setPreferredSize(new java.awt.Dimension(700, 20));
        DialogPanel.setLayout(new java.awt.BorderLayout());

        RobbieExpressionPanel.setBackground(new java.awt.Color(0, 0, 0));
        RobbieExpressionPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        RobbieExpressionPanel.setForeground(new java.awt.Color(204, 204, 204));
        RobbieExpressionPanel.setMaximumSize(new java.awt.Dimension(100, 200));
        RobbieExpressionPanel.setMinimumSize(new java.awt.Dimension(100, 200));
        RobbieExpressionPanel.setPreferredSize(new java.awt.Dimension(100, 200));
        DialogPanel.add(RobbieExpressionPanel, java.awt.BorderLayout.WEST);

        RobbieDiaPanel.setBackground(new java.awt.Color(0, 0, 0));
        RobbieDiaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        RobbieDiaOut.setBackground(new java.awt.Color(0, 0, 0));
        RobbieDiaOut.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Robbie:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 51, 0))); // NOI18N

        robbieDiaOut.setBackground(new java.awt.Color(255, 255, 204));
        robbieDiaOut.setColumns(20);
        robbieDiaOut.setEditable(false);
        robbieDiaOut.setLineWrap(true);
        robbieDiaOut.setRows(10);
        jScrollPane1.setViewportView(robbieDiaOut);

        org.jdesktop.layout.GroupLayout RobbieDiaOutLayout = new org.jdesktop.layout.GroupLayout(RobbieDiaOut);
        RobbieDiaOut.setLayout(RobbieDiaOutLayout);
        RobbieDiaOutLayout.setHorizontalGroup(
            RobbieDiaOutLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RobbieDiaOutLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 500, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        RobbieDiaOutLayout.setVerticalGroup(
            RobbieDiaOutLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, RobbieDiaOutLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane4.setLeftComponent(RobbieDiaOut);

        RobbieDiaIn.setBackground(new java.awt.Color(0, 0, 0));
        RobbieDiaIn.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "You:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        RobbieDiaIn.setMinimumSize(new java.awt.Dimension(700, 51));
        RobbieDiaIn.setPreferredSize(new java.awt.Dimension(700, 138));
        RobbieDiaIn.setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(BotViewColor.getColor(BotViewColor.Panel));
        jPanel1.setPreferredSize(new java.awt.Dimension(696, 70));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        parsedAnswer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        parsedAnswer.setMinimumSize(new java.awt.Dimension(600, 22));
        parsedAnswer.setPreferredSize(new java.awt.Dimension(600, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(parsedAnswer, gridBagConstraints);

        userAnswer.setBackground(new java.awt.Color(255, 255, 204));
        userAnswer.setMinimumSize(new java.awt.Dimension(600, 22));
        userAnswer.setPreferredSize(new java.awt.Dimension(600, 22));
        userAnswer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                userAnswerKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(userAnswer, gridBagConstraints);

        userAnswerButton.setText("Send");
        userAnswerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userAnswerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(userAnswerButton, gridBagConstraints);

        RobbieDiaIn.add(jPanel1);

        jSplitPane4.setRightComponent(RobbieDiaIn);

        org.jdesktop.layout.GroupLayout RobbieDiaPanelLayout = new org.jdesktop.layout.GroupLayout(RobbieDiaPanel);
        RobbieDiaPanel.setLayout(RobbieDiaPanelLayout);
        RobbieDiaPanelLayout.setHorizontalGroup(
            RobbieDiaPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RobbieDiaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jSplitPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RobbieDiaPanelLayout.setVerticalGroup(
            RobbieDiaPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, RobbieDiaPanelLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jSplitPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 281, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        DialogPanel.add(RobbieDiaPanel, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(DialogPanel);

        getContentPane().add(jSplitPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void robbieStartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_robbieStartButtonActionPerformed
// TODO add your handling code here:

        robbieStartButton.setEnabled(false);

        botMgrInst = new BotMgr();
        botMgrInst.setView(this);
        botMgrInst.addObserver(this);

        try {
            myBot = botMgrInst.createBot(this, this, "RobbieBot");
            if (myBot.isOk()) {
                this.showBrainStatus(BrainStatus.Alive, BrainStatusState.On);
                myBot.setView(this);
                myBot.addObserver(this);
                this.myBrainCortex = myBot.getBrainCortex();
                this.conIndicator.setEnabled(myBot.becomeAware());
            }
        }
        catch (RobbieBotException ex) {
            Logger.getLogger(BotViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (BrainFrameException ex) {
            Logger.getLogger(BotViewer.class.getName()).log(Level.SEVERE, null, ex);
        }

        robbieAnime.setEmote(EmoteSequence.Idle);
        robbieStartButton.setText("Started");

    //TODO: test

}//GEN-LAST:event_robbieStartButtonActionPerformed

private void userAnswerKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_userAnswerKeyReleased
{//GEN-HEADEREND:event_userAnswerKeyReleased
    // TODO add your handling code here:
    char c = evt.getKeyChar();
    this.answer.append(c);
    if( Character.isSpaceChar(c))
    {
        System.out.println("Space");
    }
}//GEN-LAST:event_userAnswerKeyReleased

private void userAnswerButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_userAnswerButtonActionPerformed
{//GEN-HEADEREND:event_userAnswerButtonActionPerformed
    // TODO add your handling code here:
    String s = this.userAnswer.getText();
    System.out.println("Answer is: " + s );
    answerPhrase = new Phrase(s);
    // send the answer to the brain to process
    ThoughtObject to = new ThoughtObject( this, ThoughtType.Answer, ModiferType.Asked, answerPhrase);
    if( answerObject == null )
    {
        answerObject = new AnswerObject( to );
    }
    else
    {
        answerObject.assignToObject(to);
    }
    this.myBrainCortex.questionAskedExpectReply(answerObject);




}//GEN-LAST:event_userAnswerButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {

                BotViewer bv = new BotViewer();
                bv.setVisible(true);
                bv.setupRobbieAnime();

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DialogPanel;
    private javax.swing.JPanel RobbieControlPanel;
    private javax.swing.JPanel RobbieDiaIn;
    private javax.swing.JPanel RobbieDiaOut;
    private javax.swing.JPanel RobbieDiaPanel;
    private javax.swing.JPanel RobbieExpressionPanel;
    private javax.swing.JPanel RobbieOut;
    private javax.swing.JLabel aliveIndicator;
    private javax.swing.JLabel blueLed;
    private javax.swing.JLabel conIndicator;
    private javax.swing.JPanel conscienencePanel;
    private javax.swing.JTextArea consciousOut;
    private javax.swing.JLabel greenLed;
    private javax.swing.JPanel indicationPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JLabel learnIndicator;
    private javax.swing.JLabel loopIndicator;
    private javax.swing.JLabel offLed;
    private javax.swing.JLabel parsedAnswer;
    private javax.swing.JLabel purpleLed;
    private javax.swing.JLabel questionIcon;
    private javax.swing.JLabel redLed;
    private javax.swing.JTextArea robbieDiaOut;
    private javax.swing.JLabel robbieIdValue;
    private javax.swing.JButton robbieStartButton;
    private javax.swing.JLabel robbieStatusIcon;
    private javax.swing.JLabel subConIndicator;
    private javax.swing.JPanel subConsciencePanel;
    private javax.swing.JTextArea subConsciousOut;
    private javax.swing.JLabel thinkIndicator;
    private javax.swing.JTextField userAnswer;
    private javax.swing.JButton userAnswerButton;
    private javax.swing.JLabel yellowLed;
    // End of variables declaration//GEN-END:variables
    private BotMgr botMgrInst;

    /**
     *
     * @param msg
     */
    public void say(String msg)
    {
        showBrainStatus(BrainStatus.Conscious, BrainStatusState.On);
        consciousOut.append(msg);
        consciousOut.append("\n");
        showBrainStatus(BrainStatus.Conscious, BrainStatusState.Off);
    }

    /**
     *
     * @param msg
     */
    @Override
    public void logMessage(String msg)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param thought
     */
    @Override
    public void consciousThought(ThoughtObject thought)
    {
        showBrainStatus(BrainStatus.Conscious, BrainStatusState.On);
        consciousOut.append(thought.toString());
        consciousOut.append("\n");
        showBrainStatus(BrainStatus.Conscious, BrainStatusState.Off);

    }

    /**
     *
     * @param thought
     */
    @Override
    public void subConsciousThought(ThoughtObject thought)
    {
        showBrainStatus(BrainStatus.Subconscious, BrainStatusState.On);
        subConsciousOut.append(thought.toString());
        subConsciousOut.append("\n");
        showBrainStatus(BrainStatus.Subconscious, BrainStatusState.Off);
    }

    /**
     *
     */
    @Override
    public void statusGrey()
    {
        setStatusIcon(BotStatusColor.Grey);
    }

    /**
     *
     */
    @Override
    public void statusGreen()
    {
        setStatusIcon(BotStatusColor.Green);
    }

    /**
     *
     */
    @Override
    public void statusRed()
    {
        setStatusIcon(BotStatusColor.Red);
    }

    /**
     *
     */
    public void statusPurple()
    {
        setStatusIcon(BotStatusColor.Purple);
    }

//    private void setStatus(BotStatus status, boolean flag)
//    {
//        switch (status) {
//        case Loop:
//            if (flag) {
//                subConIndicator.setIcon(greenLed.getIcon());
//            }
//            else {
//                subConIndicator.setIcon(redLed.getIcon());
//            }
//            break;
//        case Think:
//            if (flag) {
//                thinkIndicator.setIcon(purpleLed.getIcon());
//            }
//            else {
//                thinkIndicator.setIcon(offLed.getIcon());
//            }
//            break;
//        case Question:
//            if (flag) {
//                questionIcon.setEnabled(flag);
//            }
//            break;
//        case Learn:
//            if (flag) {
//                learnIndicator.setIcon(greenLed.getIcon());
//            }
//            else {
//                learnIndicator.setIcon(offLed.getIcon());
//            }
//            break;
//        }
//    }

    private void setLoopStatus()
    {
    }

    private void setStatusIcon(BotStatusColor status)
    {
        switch (status) {
        case Grey:
            this.robbieStatusIcon.setIcon(this.offLed.getIcon());
            break;

        case Green:
            this.robbieStatusIcon.setIcon(this.greenLed.getIcon());
            break;

        case Yellow:
            break;

        case Red:
            this.robbieStatusIcon.setIcon(this.redLed.getIcon());
            break;

        case Purple:
            this.robbieStatusIcon.setIcon(this.purpleLed.getIcon());
            break;


        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void setBotId(String id)
    {
        this.robbieIdValue.setText(id);
    }

    /**
     *
     * @param status
     * @param color
     */
    @Override
    public void setStatusIcon(BotStatus status, BotStatusColor color)
    {
        setStatusIcon(color);
    }

    /**
     *
     * @param status
     * @param flag
     */
    @Override
    public void setBotStatus(BotStatus status, boolean flag)
    {
//        setStatus(status, flag);
    }

    /**
     *
     * @param thought
     */
    @Override
    public void say(ThoughtObject thought)
    {
        this.say(thought.toString());
    }

    /**
     *
     * @param status
     * @param state
     */
    @Override
    public void showBrainStatus(BrainStatus status, BrainStatusState state)
    {
        boolean on = state.equals(BrainStatusState.On);
        boolean off = state.equals(BrainStatusState.Off);
        boolean unk = state.equals(BrainStatusState.Unknown);
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(BotViewer.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (status) {
        case Alive:
            aliveIndicator.setEnabled(true);
            if (on) {
                this.aliveIndicator.setIcon(this.greenLed.getIcon());
                System.out.println("Bot is Alive");
            }
            else {
                this.aliveIndicator.setIcon(this.redLed.getIcon());
                System.out.println("Bot is Dead!!....");

            }
            break;
        case Conscious:
            this.conIndicator.setEnabled(true);
            if (on) {
                this.conIndicator.setIcon(this.blueLed.getIcon());
            }
            else if (off) {
                this.conIndicator.setIcon(this.offLed.getIcon());
            }
            else {
                this.conIndicator.setIcon(this.redLed.getIcon());
            }
            break;


        case Subconscious:
            this.subConIndicator.setEnabled(true);
            if (on) {
                this.subConIndicator.setIcon(this.blueLed.getIcon());
            }
            else if (off) {
                this.subConIndicator.setIcon(this.offLed.getIcon());
            }
            else {
                this.subConIndicator.setIcon(this.redLed.getIcon());
            }
            break;

        case Locked:
            this.loopIndicator.setEnabled(true);
            if (on) {
                this.loopIndicator.setIcon(this.yellowLed.getIcon());
            }
            else {
                this.loopIndicator.setIcon(this.offLed.getIcon());
            }
            break;
        case Learn:
            this.learnIndicator.setEnabled(true);
            if(on)
            {
                this.learnIndicator.setIcon(this.blueLed.getIcon());
            }
            else {
                this.learnIndicator.setIcon(this.offLed.getIcon());
            }
            break;
        case Deep:
            this.thinkIndicator.setEnabled(true);
            if (on) {
                this.thinkIndicator.setIcon(this.blueLed.getIcon());

            }
            else {
                this.thinkIndicator.setIcon(this.offLed.getIcon());
            }
            break;

        }

    }

    /**
     *
     * @param to
     */
    @Override
    public void showThought(ThoughtObject to)
    {
        ModiferType tm = to.getModifer();
        ThoughtType tt = to.getType();

        switch (tm) {
        case Indicate:
            if (tt.compareTo(ThoughtType.Deep) == 0) {
////                this.indicationQuestion(false);
                showBrainStatus(BrainStatus.Deep, BrainStatusState.On);
//                setStatus(BotStatus.Think, true);
            }
            else if(tt.compareTo(ThoughtType.Learn) == 0)
            {
                showBrainStatus(BrainStatus.Learn, BrainStatusState.On);
            }
            else if(tt.compareTo(ThoughtType.Locked) == 0)
            {
                showBrainStatus(BrainStatus.Locked, BrainStatusState.On);
            }
            else if(tt.compareTo(ThoughtType.Question) == 0)
            {
                this.indicationQuestion(true);
                showBrainStatus(BrainStatus.Learn, BrainStatusState.On);
            }

            break;
        }
    }
}
