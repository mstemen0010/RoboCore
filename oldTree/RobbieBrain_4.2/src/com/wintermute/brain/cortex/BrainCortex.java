/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain.cortex;

import com.wintermute.brain.BrainInterface;
import com.wintermute.brain.BrainListenerInterface;
import com.wintermute.brain.ObjectNode;
import com.wintermute.brain.frame.BrainFrame;
import com.wintermute.brain.thoughtarray.AnswerObject;
import com.wintermute.brain.thoughtarray.ThoughtObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 *
 * @author mstemen
 */
public class BrainCortex extends Observable
{
    private ArrayList<BrainCortexListener> listeners = new ArrayList<BrainCortexListener>();
    private BrainInterface myBrain = null;

    /**
     *
     * @param brain
     */
    public BrainCortex(BrainInterface brain)
    {
        myBrain = brain;
    }

    /**
     *
     * @return
     */
    public int getProcessTime()
    {
        return myBrain.getProcessTime();
    }
    /**
     *
     * @param to
     * @return
     */
    public ThoughtObject think( ThoughtObject to )
    {
        return myBrain.think(to);
    }// generate a new thought based on another

    /**
     *
     * @param input
     * @return
     */
    public ThoughtObject think(String input)
    {
        return myBrain.think(input);
    }

    /**
     *
     * @return
     */
    public ThoughtObject think()
    {
        return myBrain.think();
    }

    /**
     *
     * @param cortexListener
     */
    public void addListener(BrainCortexListener cortexListener)
    {
        listeners.add(cortexListener);

    }

    /**
     *
     * @param to
     */
    public void questionAskedExpectReply(AnswerObject to)
    {
       this.myBrain.questionAskedExpectReply(to);
    }

    /**
     *
     * @param to
     */
    public void showThought(ThoughtObject to){
        Iterator<BrainCortexListener> i = listeners.iterator();
        this.setChanged();
        this.notifyObservers();

        while (i.hasNext())
        {
            BrainCortexListener l = i.next();
            
        }
    }
    /**
     * Does ...
     *
     * @param input ...
     */
    public void look(String input)
    {
        this.myBrain.look(input);
    }

    /**
     * Does ...
     *
     * @param input ...
     */
    public void say(String input){
        this.myBrain.say(input);
    }

    /**
     *
     * @param to
     */
    public void say(ThoughtObject to )
    {
        this.setChanged();
        this.notifyObservers(to);
        // this.myBrain.say(to);
    }

    

    /**
     * Does ...
     *
     * @param input ...
     */
    public void am(String input)
    {
        this.myBrain.am(input);
    }

    /**
     * Does ...
     *
     * @param thought
     */
    public void send(ThoughtObject thought)
    {
        this.myBrain.send(thought);
    }

    /**
     *
     * @param inNode
     */
    public void relate(ObjectNode inNode)
    {
        this.myBrain.relate(inNode);
    }

//    public boolean isReady(){;}

//    public boolean isAlive(BrainInterface bi){};

//    public int getProcessTime(){;}

//    void setProcessTime( int newProcessTime ); // this is package wide, but it is up to the Brain to decide to synchronize or not

//    public void addListener(BrainListenerInterface listener);

    // for testing memory, not remembering/learning something
//    public ThoughtObject remember( ThoughtObject thoughtObject );

//    public void setBrainFrame( BrainFrame bf );

}
