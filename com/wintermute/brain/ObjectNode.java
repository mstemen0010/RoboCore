/*
 * ObjectNode.java
 *
 * Created on August 10, 2001, 5:01 PM 5:01 PM
 */

package com.wintermute.brain;

import com.wintermute.nlp.grammar.GrammarToolkit;
import java.util.*;


/**
 *
 * @author  unknown
 * @version 
 */
public class ObjectNode extends java.lang.Object {

    /** an Object Node is a class that is used to represent a tangiable "thing"
     *  (i.e. a ball, house, color, big, small)
     *
     *  A Object Node may contain references to other object nodes that is 
     *  stored in it's "description"
     */
    
    private Hashtable description = new Hashtable();
    private String name = null;
    private GrammarToolkit gtk = GrammarToolkit.getDefaultGrammarToolkit();
    
    /** Creates new ObjectNode */
    public ObjectNode(String name) {
        this.name = name;                
    }
    public void modify(ObjectNode newMod) {
        description.put(newMod.name, newMod);
    }
    public String name() {
        return name;
    }
    public String describeSelf() {
        StringBuffer sb = new StringBuffer();    
        sb.append(gtk.getArticle(name));
        sb.append("  is ");        

        Enumeration e = description.keys(); // the keys are the "names" of other object nodes that "describe" this one
        while (e.hasMoreElements()) {            
            sb.append((String) e.nextElement());
        }
        return sb.toString();
    }

}
