/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package com.wintermute.brain.center;

import com.wintermute.brain.*;
import com.wintermute.brain.thoughtarray.ThoughtObject;
import com.wintermute.brain.thoughtarray.ThoughtPattern;
import com.wintermute.brain.thoughtarray.ThoughtConsumer;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;
import java.util.*;

/**
 * @author  unknown
 * @version
 */
public class CognitiveCenter extends Object implements BrainCenterInterface, ThoughtConsumer {
    ///////////////////////////////////////
    //operations
    /**
     * Creates new CognitiveCenter
    
     */
    private BrainInterface brainCortex;
    private boolean answerPending;
    
    /**
     *
     * @param brainCortex
     */
    public CognitiveCenter( BrainInterface brainCortex ) {
        this.brainCortex = brainCortex;
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


    /**
     * Does ...
     * 
     * @return A ThoughtObject with ...
     * @param thought
     */
    @Override
    public ThoughtObject process(ThoughtObject thought) {
        // we are getting a thought, we need to identify it
        // are we self aware
        if (thought.getType().equals(ThoughtType.SelfAware) && thought.getModifer().equals(ModiferType.Is)) {
            ThoughtObject t = brainCortex.remember(thought);
        }
        return null;
    }
    
    /**
     *
     * @param thought
     * @return
     */
    public ThoughtObject analyse( ThoughtObject thought )
    {
        ThoughtObject newThought = null;
        String rt = thought.toString().toLowerCase();
        
        if( thought.getType().equals( ThoughtType.Unknown) && thought.getModifer().equals( ModiferType.Unknown ) )// a quick thought, have to look at the string
        {
            if( rt.contains("who") || rt.contains("what") )
            {
                newThought = new ThoughtObject( this, ThoughtType.Question, ModiferType.Slow );
            }
                
        }
        
        return newThought;
    }
    
    /**
     * 
     * @param thought
     */
    @Override
    public void send(ThoughtObject thought) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param thought
     */
    @Override
    public void learn(ThoughtObject thought) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param pattern
     */
    @Override
    public void learn(ThoughtPattern pattern) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAnswerPending() {
        return answerPending;
    }

    void answerPending(boolean flag)
    {
        answerPending = flag;
    }
}

