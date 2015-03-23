/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wintermute.brain.frame;

import com.wintermute.bot.BotViewInterface;
import com.wintermute.brain.*;
import com.wintermute.brain.center.BrainCenterInterface;
import com.wintermute.brain.cortex.BrainCortex;
import com.wintermute.brain.thoughtarray.AnswerObject;
import com.wintermute.brain.thoughtarray.ThoughtObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**import com.wintermute.net.bot.BotViewInterface;

 * 
 *Class that allows brains and brain centers to be dynamically altered without 
 * having a need to restart or stop the brain user (a bot)--basically "hot-swap" 
 */
/**
 *
 * @author mstemen
 */
public class BrainFrame implements BrainInterface, BrainFrameInterface
{ // package level class only
    
    private static BrainFrame inst;
    
    private BrainCenterInterface cogCenter;
    private BrainCenterInterface selfCenter;
    private BotViewInterface botView = null;
    private Brain lastBrain;
    
    private HashMap<String, BrainInterface> brains = new HashMap<String, BrainInterface>();
    @SuppressWarnings("empty-statement")
    private BrainFrame()
    { ; }
    
    /**
     * 
     * @return
     */
    public static BrainFrame getInstance()
    {
        if( inst == null )
        {
            inst = new BrainFrame();
        }
        return inst;
    }
        
            
    /**
     *
     * @param brainClass
     * @return
     * @throws BrainFrameException
     */
    @Override
    public BrainInterface spinUpBrain(String brainClass ) throws BrainFrameException
    {
        // look for a brain of that type first
        
        BrainInterface bi = brains.get(brainClass);
        
        if( bi == null )
        {
            Class c = null;            
            try {
                c = Class.forName(brainClass);
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(BrainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bi = (BrainInterface) c.newInstance();
                bi.setBrainFrame(this);
                brains.put(brainClass, bi);
            }
            catch (InstantiationException ex) {
                Logger.getLogger(BrainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IllegalAccessException ex) {
                Logger.getLogger(BrainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if( bi != null )
        {
            bi.setBrainFrame(this);
            lastBrain = bi.getBrain();

        }
        return bi;
//        Class brainClass = null;
//        BrainInterface bi = null;
//        try {
//            brainClass = Class.forName(brainName);
//            try {
//                // Class targetInterface = Class.forName( this.getClass().getPackage() + ".BrainInterface");
//                Class targetInterface = Class.forName( BrainInterface.class.getName() );
////                Class c = ReflectTools.respondsToInterface(brainClass, targetInterface);
//                // test to make sure c is the right interface type, just a double check... tools does this
//                try
//                {
//                    Object o;
//                    o = ReflectTools.respondsToInterface(brainClass, targetInterface);                                                
//                    targetInterface
//                    bi = targetInterface;
//                }
//                catch( ClassCastException e )
//                {
//                    Logger.getLogger(BrainFrame.class.getName()).log(Level.SEVERE, null, e);
//                }
//            }
//            catch (ClassException ex) {
//                Logger.getLogger(BrainFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        }
//        catch (ClassNotFoundException ex) {
//            Logger.getLogger(BrainFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
//  
//        return bi;
    }

    /**
     *
     * @return
     */
    @Override
    public Brain getBrain()
    {
        return lastBrain;
    }

    @Override
    public ThoughtObject think(ThoughtObject to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param input
     * @return
     */
    @Override
    public ThoughtObject think(String input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    @Override
    public ThoughtObject think() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void look(String input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void say(String input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param to
     */
    @Override
    public void say(ThoughtObject to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void am(String input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void send(ThoughtObject thought) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param inNode
     */
    @Override
    public void relate(ObjectNode inNode) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    @Override
    public int getProcessTime() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param newProcessTime
     */
    @Override
    public void setProcessTime(int newProcessTime) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param listener
     */
    @Override
    public void addListener(BrainListenerInterface listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param thoughtObject
     * @return
     */
    @Override
    public ThoughtObject remember(ThoughtObject thoughtObject) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isStreamBlocked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    @Override
    public boolean wasBlocked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param blocked
     */
    @Override
    public void setBlocked(boolean blocked) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     *
     * @param bInf
     * @return
     */
    @Override
    public boolean isAlive(BrainInterface bInf)
    {
        boolean bf = false;
        if( brains == null )
            return false;
        if( brains.values() == null )
            return false;
        Iterator<BrainInterface> bI = this.brains.values().iterator();
        if( bI == null )
            return false;
        while( bI.hasNext())
        {
            BrainInterface BrInf = bI.next();
            if( bInf == BrInf )
            {
                bf = true;
            }
        }

        return bf;
    }

    /**
     *
     * @param bf
     */
    @Override
    public void setBrainFrame(BrainFrame bf)
    {
        throw new UnsupportedOperationException("Not supported yet.");
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
     * @return
     */
    @Override
    public boolean isAlive()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    @Override
    public boolean tryAware()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param botView
     */
    @Override
    public void setBotView(BotViewInterface botView)
    {
        this.botView= botView;
    }

    /**
     *
     * @return
     */
    @Override
    public BrainFrameInterface getBrainFrame()
    {
        return this;
    }

    /**
     *
     * @param obs
     */
    @Override
    public void addObserver(Observer obs)
    {
        
    }

    /**
     *
     * @param request
     */
    @Override
    public void questionAskedExpectReply(AnswerObject request)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BrainInterface getBrainInterface() {
        return this;
    }

    @Override
    public BrainCortex getBrainCortex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
