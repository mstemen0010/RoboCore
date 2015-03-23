/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package com.wintermute.brain;

import java.util.*;
import java.util.Hashtable;

/**
 * @author  unknown
 * @version
 */

public class KnowledgeBase extends Object {
  ///////////////////////////////////////
  //attributes
/**
 * A KnownledgeBase is a collection of ObjectNodes

 */

  private Hashtable base =  new Hashtable();
  public void setBase(Hashtable _base){ base = _base; }
  public Hashtable getBase(){ return base; }



  ///////////////////////////////////////
  //operations
/**
 * Does ...
 * 

 */
   public  KnowledgeBase() {
  }

}

