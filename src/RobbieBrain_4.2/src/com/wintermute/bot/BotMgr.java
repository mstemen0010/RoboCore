/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wintermute.bot;

import com.wintermute.brain.BrainInterface;
import com.wintermute.brain.BrainMgr;
import com.wintermute.brain.cortex.BrainCortexListener;
import com.wintermute.brain.frame.BrainFrameException;
import com.wintermute.brain.thoughtarray.ThoughtObject;
import java.util.HashMap;
import java.util.Observable;

/**
 *
 * @author mstemen
 */
public class BotMgr extends Observable implements BotViewInterface
{

    private BotMgr botMgrInstance;
    private RobbieBot robbie;
    private BrainInterface robbieBrain;
    private RobbieBotInterface robbieInterface;    
    private Thread robbieThread = null;
    private BotViewInterface botView = null;
    private HashMap<BotViewInterface, RobbieBot> managedBots = null;

    /**
     *
     */
    public BotMgr()
    {
        managedBots = new HashMap<BotViewInterface, RobbieBot>();
    }

    /**
     *
     * @param view
     */
    public void setView(BotViewInterface view)
    {
        botView = view;
    }

    /**
     *
     * @param viewInf
     * @param botToManage
     */
    public void setHardwire( BotViewInterface viewInf, RobbieBot botToManage)
    {
        managedBots.put(viewInf, botToManage);

    }

    /**
     *
     * @param cortexListener
     * @param botListener
     * @param botName
     * @return
     * @throws RobbieBotException
     * @throws BrainFrameException
     */
    public RobbieBot createBot(BrainCortexListener cortexListener, BotListener botListener, String botName) throws RobbieBotException, BrainFrameException
    {
        if (botView == null) {
            throw new RobbieBotException("View must be set first, call setView before using this method");
        }
        robbieBrain = BrainMgr.getInstance().spinUpBrain("RobbieBrain");

        
        robbie = new RobbieBot(this, 100);
        // robbie.addObserver(cortexListener);
        robbieThread = new Thread(robbie);
        robbie.setBrain(robbieBrain);
        try {
            robbie.initBrain(50);
        }
        catch (RobbieBotException ex) {
            botView.logMessage("Caught RobbieBotException: " + ex.getMessage());
        }
//        robbieThread = new Thread(robbie);
        robbieThread.start();
        robbieThread.setName("RobbieThread");

        return robbie;
    }

    /**
     *
     * @param msg
     */
    public void say(String msg)
    {
        ThoughtObject to = new ThoughtObject(msg);
        this.botView.say(to);
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
    }

    /**
     *
     * @param thought
     */
    @Override
    public void subConsciousThought(ThoughtObject thought)
    {
        this.botView.subConsciousThought(thought);
    }

    /**
     *
     */
    @Override
    public void statusGreen()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     */
    @Override
    public void statusGrey()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     */
    @Override
    public void statusRed()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param id
     */
    @Override
    public void setBotId(String id)
    {
        this.botView.setBotId(id);
    }

    /**
     *
     * @param status
     * @param color
     */
    @Override
    public void setStatusIcon(BotStatus status, BotStatusColor color)
    {
        this.botView.setStatusIcon(status, color);
    }

    /**
     *
     * @param status
     * @param flag
     */
    @Override
    public void setBotStatus(BotStatus status, boolean flag)
    {
//        this.botView.setBotStatus(status, flag);
    }

    /**
     *
     * @param thought
     */
    @Override
    public void say(ThoughtObject thought)
    {
        String msg = thought.toString();
        this.botView.say(thought);
    }

    /**
     *
     * @param to
     */
    @Override
    public void showThought(ThoughtObject to)
    {
        botView.showThought(to);
    }

    /**
     *
     */
    @Override
    public void indicateQuestion()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
