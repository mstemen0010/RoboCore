/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain;

import com.wintermute.brain.thoughtarray.ThoughtObject;

/**
 *
 * @author mstemen
 */
public interface BrainListenerInterface {
    
    public void hear( ThoughtObject thought );
    
    public void hear( String innerThought );

}
