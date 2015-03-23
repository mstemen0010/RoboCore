/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain.thoughtarray;

import com.wintermute.brain.center.Synapse;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;

/**
 *
 * @author mstemen
 */
public class ThoughtPatternSynapse {

    Synapse thoughtSynapse;
    
    ThoughtPatternSynapse( ThoughtPattern pattern, ThoughtType type, ModiferType modType )
    {
        thoughtSynapse = new Synapse( type, modType );
        
    }
    
    /**
     *
     * @return
     */
    public Synapse getSynapse()
    {
        return thoughtSynapse;
    }
}
