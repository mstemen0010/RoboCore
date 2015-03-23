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
import java.util.*;

/**
 * @author  unknown
 * @version
 */

public class ListeningCenter extends Object implements BrainCenterInterface {


  ///////////////////////////////////////
  //operations
/**
 * Creates new SpeechCenter

 */
   private BrainInterface cortex = null;
    private boolean answerPending;
   
   /**
    *
    * @param cortext
    */
   public ListeningCenter( BrainInterface cortext ) {
       this.cortex = cortex;
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

    /**
     *
     * @param thought
     */
    @Override
    public void send(ThoughtObject thought) {
        cortex.send(thought);
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

