/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package com.wintermute.brain.center;

import com.wintermute.brain.thoughtarray.ThoughtObject;
import com.wintermute.brain.thoughtarray.ThoughtPattern;

/**
 * @author  unknown
 * @version
 */

public interface BrainCenterInterface {


  ///////////////////////////////////////
  //operations
/**
 * Does ...
 * 
 * @return A ThoughtObject with ...
 * @param thought
 */
  public ThoughtObject process(ThoughtObject thought);
  
  /**
   *
   * @param thought
   */
  public void send( ThoughtObject thought );

  public boolean isAnswerPending();
  
  /**
   *
   * @param thought
   */
  public void learn( ThoughtObject thought );
  
  /**
   *
   * @param pattern
   */
  public void learn( ThoughtPattern pattern );
  
  
}

