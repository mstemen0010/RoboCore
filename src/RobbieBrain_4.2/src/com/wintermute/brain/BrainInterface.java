/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package com.wintermute.brain;

import com.wintermute.brain.cortex.BrainCortex;
import com.wintermute.brain.frame.BrainFrame;
import com.wintermute.brain.frame.BrainFrameInterface;
import com.wintermute.brain.thoughtarray.AnswerObject;
import com.wintermute.brain.thoughtarray.ThoughtObject;
import java.util.Observer;

/**
 * @author  unknown
 * @version
 */
public interface BrainInterface extends BrainStreamInterface {
    ///////////////////////////////////////
    //operations
    /**
     * Does ...
     * 
     * @return A String with ...
     * @param input ...
     */
    
    public ThoughtObject think( ThoughtObject to ); // generate a new thought based on another
    
    public ThoughtObject think(String input);

    public ThoughtObject think();

    public BrainInterface getBrainInterface();

    public BrainCortex getBrainCortex();

    public void showThought(ThoughtObject to);
    /**
     * Does ...
     * 
     * @return A void with ...
     * @param input ...
     */
    public abstract void look(String input);

    /**
     * Does ...
     * 
     * @return A void with ...
     * @param input ...
     */
    public abstract void say(String input);
    
    public abstract void say(ThoughtObject to );

    /**
     * Does ...
     * 
     * @return A void with ...
     * @param input ...
     */
    public abstract void am(String input);

    /**
     * Does ...
     * 
     * @param inNode ...
     */
    public void send(ThoughtObject thought);

    public abstract void relate(ObjectNode inNode);

    public boolean isReady();

    public boolean isAlive(BrainInterface bi);

    public boolean isAlive();
    
    public int getProcessTime();
    
    void setProcessTime( int newProcessTime ); // this is package wide, but it is up to the Brain to decide to synchronize or not

    public void addListener(BrainListenerInterface listener);

    public void addObserver(Observer obs );
    
    // for testing memory, not remembering/learning something
    public ThoughtObject remember( ThoughtObject thoughtObject );

    public void setBrainFrame( BrainFrame bf );

    public boolean tryAware();

    public BrainFrameInterface getBrainFrame();

    public void questionAskedExpectReply(AnswerObject answer);

    public Brain getBrain();
}

