/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain.center;

import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;

/**
 *
 * @author mstemen
 */
public class Synapse {
    
    ThoughtType myType;
    ModiferType myModType;
           
    /**
     *
     * @param type
     * @param modType
     */
    public Synapse( ThoughtType type, ModiferType modType )
    {
        myType = type;
        myModType = modType;
    }
    
    @Override
    public String toString()
    {
        return myType.toString() + myModType.toString();
    }
    /**
     *
     * @param synapse
     * @return
     */
    public boolean equals(Synapse synapse )
    {
        return (myType.toString().equals(synapse.myType.toString()) && myModType.toString().equals(synapse.myModType.toString()));
    }
    /**
     *
     * @param typeToTest
     * @param modToTest
     * @return
     */
    public boolean equals( ThoughtType typeToTest, ModiferType modToTest )
    {
        return (myType.toString().equals(typeToTest.toString()) && myModType.toString().equals(modToTest.toString()));
    }
}
