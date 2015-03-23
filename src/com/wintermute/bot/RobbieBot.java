/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wintermute.bot;


import com.wintermute.brain.BrainInterface;
import com.wintermute.brain.BrainListenerInterface;
import com.wintermute.brain.thoughtarray.ThoughtConsumer;
import com.wintermute.brain.thoughtarray.ThoughtObject;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;
import com.wintermute.bot.BotViewInterface.BotStatus;
import com.wintermute.bot.BotViewInterface.BotStatusColor;
import com.wintermute.brain.RobbieBrain;
import com.wintermute.brain.cortex.BrainCortex;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mstemen
 */
public class RobbieBot extends java.util.Observable implements Runnable, RobbieBotInterface, BrainListenerInterface, ThoughtConsumer  {

    boolean running = false;
    private boolean isInit = false;
    
    int sleepTime = 1000;
    int initSleepTime = 1000;
    private boolean isRunning = false;
    private String botName = null;
    private BotViewInterface ownerInterface;
    private BrainInterface robbieBrain = null;
    private BotListener robbieListener = null;
    private Thread brainThread = null;
    private RobbieBrain myBrain = null;
    private BrainCortex brainCortex = null;

    // Constructor for method with no arguments
    /**
     *
     * @param ownerInterface
     * @param sleepTime
     * @throws RobbieBotException
     */
    public RobbieBot(BotViewInterface ownerInterface, int sleepTime) throws RobbieBotException {
        this.ownerInterface = ownerInterface;
        botName = new String(RobbieBot.class.getSimpleName() + "$$" + this.hashCode() + ": ");
        ownerInterface.setBotId(botName);
    }

    /**
     *
     * @return
     */
    @Override
    public String getName()
    {
        return this.getClass().getSimpleName();
    }

    @Override
    public void addObserver(Observer listener )
    {
        super.addObserver(listener);
        this.robbieListener = (BotListener) listener;
        this.robbieBrain.addObserver(listener);
        
    }

    /**
     *
     * @param botView
     */
    public void setView(BotViewInterface botView)
    {
        this.robbieBrain.getBrainFrame().setBotView(botView);

    }

    /**
     *
     * @return
     */
    public boolean becomeAware()
    {
        return this.robbieBrain.tryAware();
    }


    /**
     *
     * @return
     */
    public boolean isOk()
    {
        return isRunning && isInit;
    }

    /**
     *
     * @param startupSleepTime
     * @throws RobbieBotException
     */
    public void initBrain(int startupSleepTime) throws RobbieBotException {
        running = true;
        int waitCount = 10;
        int loopCount = 0;
        try {
            Thread.sleep(startupSleepTime * 5);
            // see if the iterface is ready
            if( ownerInterface != null )
            {
                quickThought("Coming online...");
            }
            
            quickThought( "Now if I only had a brain...");
            while( this.robbieBrain == null )
            {                   
                Thread.sleep( startupSleepTime * 10 );
                quickThought( "Still no brain :( ");
                loopCount++;
                if( waitCount > loopCount )
                {
                    this.ownerInterface.setStatusIcon(BotStatus.Loop, BotStatusColor.Red);
                    quickThought( "My brain hurts, please check log");
                    throw new RobbieBotException( "Brain failed to start, robbie death");                                       
                }
            }
            if( robbieBrain != null && brainThread == null)
            {
                System.out.println("RobbieBot::initBrain: Starting Brain Operations...");
                myBrain = (RobbieBrain) robbieBrain.getBrain();
                this.brainThread = new Thread(myBrain);
                this.brainThread.start();
                System.out.println("RobbieBot::initBrain: Done!...");
            }
            loopCount = 0;
//            ownerInterface.setBotStatus(BotStatus.Loop, true);
            quickThought( "Got a thought! ");
            // use reflection to access the class and method
        } catch (InterruptedException ex) {
            Logger.getLogger(RobbieBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        if( myBrain != null )
        {
            this.brainCortex = myBrain.getBrainCortex();
        }
        isRunning = true;
        isInit = true;
        
    // use reflection to access the class and method
    }

    @Override
    public void run() {
        quickThought("Starting deep thought");
        ThoughtObject currentThought = robbieBrain.think();
        while ( isRunning ) {
            
            if( currentThought != null && currentThought.isWaitingForReply() )
            {
                // get the reply
            }
            else
            {
                if(robbieBrain.isAlive())
                {
                    currentThought = robbieBrain.think();
                }
            }

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                logMessage(ex.getMessage());
                Logger.getLogger(RobbieBot.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        logMessage("Bot exiting...");
    }

    /**
     *
     * @return
     */
    public boolean isBotRunning() {
        return isRunning;
    }

    /**
     *
     * @return
     */
    public boolean checkForExit() {
        if (!running) {
            logMessage("Exited");
        }

        return !running;
    }
    
    private ThoughtObject quickThought( String msg )
    {
        ThoughtObject t = new ThoughtObject( this, ThoughtType.Inner, ModiferType.Fast );
        t.setRawThought(msg);
        think(t);
        return t;
    }

    @Override
    protected void finalize() {
        logMessage("Robbie done!");
    }

    /**
     *
     * @param newRunState
     */
    public void setRunState(boolean newRunState) {
        running = newRunState;
        isRunning =
                running;
    }

    /**
     *
     * @return
     */
    public String getBotName() {
        return botName;
    }

    /**
     *
     * @param msg
     */
    @Override
    public void logMessage(String msg) {
        StringBuilder sb = new StringBuilder(botName);
        sb.append(": ").append(msg);
        if (ownerInterface != null) {
            ownerInterface.logMessage(sb.toString());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public RobbieBotInterface sendInterface() {
        return this;
    }

    /**
     *
     * @param newBrain
     */
    @Override
    public void setBrain(BrainInterface newBrain) {
        this.robbieBrain = newBrain;
        newBrain.addListener(this);
    }
    
    private void speak( ThoughtObject thought )
    {
        if( this.ownerInterface != null )
        {
            thought.setRawThought(botName + thought.toString());
            ownerInterface.say(thought);
        }
    }

    /**
     *
     * @param thought
     */
    @Override
    public void hear(ThoughtObject thought) {
        speak( thought );
    }

    /**
     *
     * @param innerThought
     */
    @Override
    public void hear(String innerThought) {
        ThoughtObject to = new ThoughtObject(null);
        to.setRawThought("((" + innerThought + "))");
        speak( to );
    }

    /**
     *
     * @param thought
     */
    @Override
    public void say(ThoughtObject thought) {
        ownerInterface.consciousThought(thought);
    }

    /**
     *
     * @param thought
     */
    @Override
    public void think(ThoughtObject thought) {        
        ownerInterface.subConsciousThought(thought);
    }

    public BrainCortex getBrainCortex()
    {
        return this.brainCortex;
    }
}
