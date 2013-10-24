/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain.thoughtarray;

import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;

/**
 *
 * @author mstemen
 */
public class ThoughtException extends Exception implements ThoughtConsumer {

    ThoughtObject myThought = null;
    /**
     *
     * @param msg
     */
    public ThoughtException( String msg )
    {
        super(msg);
        myThought = new ThoughtObject( this, ThoughtType.Base, ModiferType.Exception );   
        myThought.setRawThought(msg);        
    }
    
    /**
     *
     * @param type
     * @param mod
     */
    public ThoughtException( ThoughtType type, ModiferType mod )
    {
        myThought = new ThoughtObject( this, type, mod );
    }
    
     
    /**
     *
     * @param type
     * @param mod
     * @param msg
     */
    public ThoughtException( ThoughtType type, ModiferType mod, String msg )
    {
        super(msg);
        myThought = new ThoughtObject( this, type, mod );
        myThought.setRawThought(msg);
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
     *
     * @return
     */
    public ThoughtObject getThought()
    {
        return this.myThought;
    }
}
