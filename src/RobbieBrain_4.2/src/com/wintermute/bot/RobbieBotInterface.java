/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.bot;

import com.wintermute.brain.BrainInterface;
import com.wintermute.brain.thoughtarray.ThoughtObject;

/**
 *
 * @author mstemen
 */
public interface RobbieBotInterface {

    /**
     *
     * @param msg
     */
    public void logMessage( String msg );
    
    /**
     *
     * @param thought
     */
    public void say( ThoughtObject thought );
    
    /**
     *
     * @param thought
     */
    public void think( ThoughtObject thought );
    
    /**
     *
     * @param newBrain
     */
    public void setBrain( BrainInterface newBrain );
    
    /**
     *
     * @return
     */
    public RobbieBotInterface sendInterface();
}
