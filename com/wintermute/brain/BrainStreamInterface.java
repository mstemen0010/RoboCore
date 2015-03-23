/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain;

/**
 *
 * @author mstemen
 */
// interface to standardize how "blocking" done in thought streams, brain, etc
public interface BrainStreamInterface {
    
    boolean isStreamBlocked();
    
    boolean wasBlocked();
            
    void setBlocked( boolean blocked );
}
