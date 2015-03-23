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
import com.wintermute.brain.thoughtarray.ThoughtObjectKey;
import com.wintermute.brain.thoughtarray.ThoughtPattern;
import com.wintermute.nlp.structure.WordPhrase;
import java.util.*;

/**
 * @author  unknown
 * @version
 */
public class SpeechCenter extends Object implements BrainCenterInterface {
    ///////////////////////////////////////
    //operations
    /**
     * Creates new SpeechCenter
    
     */
    private BrainInterface cortex;
    private WordPhrase currentWP;

    private HashMap<ThoughtObjectKey, String> phrases = new HashMap<ThoughtObjectKey, String>();
    private boolean answerPending = false;
    /**
     *
     * @param cortex
     */
    public SpeechCenter(BrainInterface cortex) {
        this.cortex = cortex;
    }

    private void loadPhrases()
    {
        
    }
    /**
     * Does ...
     * 
     * @return A ThoughtObject with ...
     * @param thought
     */
    @Override
    public ThoughtObject process(ThoughtObject thought) {
        return null;
    }

    // read out the the phrase file that is indexed by thought type and modifier type
    private void getPhrase( ThoughtObject thought ) {
    }

    /**
     *
     * @param thought
     */
    @Override
    public void send(ThoughtObject thought) {
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

