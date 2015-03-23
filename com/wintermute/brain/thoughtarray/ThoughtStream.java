/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain.thoughtarray;

import com.wintermute.brain.*;
import com.wintermute.brain.cortex.BrainCortex;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mstemen
 */

        
public class ThoughtStream implements Runnable, Observer, BrainStreamInterface, ThoughtConsumer {



    /**
     *
     * @param input
     */
    public void say(String input)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

 
    /**
     *
     * @param to
     */
    public void say(ThoughtObject to)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     *
     * @param input
     */
    public void am(String input)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param inNode
     */
    public void relate(ObjectNode inNode)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ThoughtObject getMainThought()
    {
        ThoughtObject to;

        if( this.mainThought == null && ! thoughtStack.empty())
        {
            mainThought = getThought();
            to = mainThought;
        }
        else
        {
            to = mainThought;
        }
        return to;
    }

    public ThoughtObject seeMainThought()
    {
        return this.thoughtStack.peek();
    }

    public void setMainThought(ThoughtObject to)
    {
        to.setThoughtStream(this);
        mainThought = to;
    }
    public ThoughtObject getThought()
    {
        return this.thoughtStack.pop();
    }
    public void addThought(ThoughtObject to )
    {
        to.setThoughtStream(this);
        this.thoughtStack.push(to);
    }
    
    /**
     *
     */
    public enum ThoughtStreamType
{
        /**
         *
         */
        Undefined,
    /**
     *
     */
    ID,
    /**
     *
     */
    EGO,
    /**
     *
     */
    SUPEREGO,
    /**
     *
     */
    MAIN;
    
}
    private boolean alive = false;
    private boolean isAware = false;
    private BrainCortex brainCortex = null;
    private ThoughtObject mainThought = null;
    private ThoughtStreamType streamType = ThoughtStreamType.Undefined;
    private Stack<ThoughtObject> thoughtStack = new Stack<ThoughtObject>();  
    
    private boolean isBlocked = false;
    private boolean wasBlocked = false;

    String lostInThought = "Lost in Thought...";
    
   
    
    /**
     *
     * @param parentCortex
     */
    public ThoughtStream( BrainCortex parentCortex )
    {
        brainCortex = parentCortex;
        mainThought =  new ThoughtObject(this, ThoughtType.Unknown, ModiferType.Unknown);
        if( streamType == ThoughtStreamType.Undefined )
            streamType = ThoughtStreamType.MAIN;
    }


    /**
     *
     * @param parentCortex
     * @param streamType
     */
    public ThoughtStream( BrainCortex parentCortex, ThoughtStreamType streamType )
    {
        brainCortex = parentCortex;
        this.streamType = streamType;
    }
    
    /**
     *
     * @return
     */
    public ThoughtObject getCurrentThought()
    {
        return mainThought;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getName()
    {
        StringBuilder sb = new StringBuilder( "ThoughtStream::");
        return sb.append(this.streamType.toString()).append("::").append(this.hashCode()).append("::").toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param isBlocked
     */
    public void block( boolean isBlocked )
    {
       ThoughtObject looped;
       if( isBlocked )
       {
            looped = new ThoughtObject(this, ThoughtType.Locked, ModiferType.Indicate);
            looped.setRawThought("ThoughtObject::haveThought: brain is looped");
       }
       else
       {
            looped = new ThoughtObject(this, ThoughtType.Locked, ModiferType.Clear);
            looped.setRawThought("ThoughtObject::haveThought: brain is NOT looped");
       }

       this.brainCortex.say(looped);

        this.isBlocked = isBlocked;
        if( mainThought != null )
            this.mainThought.block(isBlocked);
    }
    
    /**
     *
     * @param initialThought
     * @return
     */
    public boolean becomeAware( ThoughtObject initialThought )
    {
        if( initialThought == null )
            return isAware;
        mainThought = initialThought;
        this.brainCortex.think( initialThought );
        isAware = true;

        return isAware;
    }

    /**
     *
     */
    public void becomeAlive()
    {
        alive = true;
    }

    private void write(String str)
    {
        String outStr = getName() + str;
        System.out.println(outStr);
    }
    
    @Override
    public void run() {
        mainThought = brainCortex.think();
        thoughtStack.push(mainThought);
         while( alive )
        {
             if( isAware && ! mainThought.isBlocked() )
            {
                // were we blocked last cycle?
                ThoughtObject to = brainCortex.think();
                ThoughtObject lastThought = thoughtStack.peek();
                if( to.equals(lastThought))
                {
                    this.wasBlocked = true;
                    //TODO this could be expanded a bit...
                    this.mainThought.block(wasBlocked);


                }
                if( to != null && to.getKey() == lastThought.getKey() )
                {
                     // same thought
                    brainCortex.say(getName() + lostInThought);
                    // brainCortex.think(to); // see if the cortex knows what to do with it
                }
                else
                {
                    mainThought = thoughtStack.pop();
                    thoughtStack.push( to );
                    lastThought = mainThought;
                    mainThought = to;
                    wasBlocked = true;
                }
                if( wasBlocked )
                {
                    wasBlocked = false;
                    // something may have changed.. notify
                   
                    brainCortex.notifyObservers();
                }
                else
                {                    
                    
                }
            }
            else
            {
                if( mainThought.isBlocked() && mainThought.isQuestion())
                {
                    this.block(true);
                    System.out.println("Got Question...");
                }
            }
            try {
                Thread.sleep(brainCortex.getProcessTime());
                brainCortex.think( mainThought ); // see if the cortex knows what to do with it
            } catch (InterruptedException ex) {
                Logger.getLogger(ThoughtStream.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public synchronized boolean isStreamBlocked() {
        return this.isBlocked;
    }

    /**
     *
     * @param blocked
     */
    @Override
    public synchronized void setBlocked(boolean blocked) {
        this.isBlocked = blocked;
    }

    /**
     * 
     * @return
     */
    @Override
    public synchronized boolean wasBlocked() {
        return this.wasBlocked;
    }
    
    // A thought stream is a continual pattern of thought it may be interrupted or altered by either memories or outside input
    // a Brain can only have one particular type of thought stream (for now at least)
    
    

}
