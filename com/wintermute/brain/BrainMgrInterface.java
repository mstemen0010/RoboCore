/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain;

import com.wintermute.brain.frame.BrainFrameException;

/**
 *
 * @author mstemen
 */
public interface BrainMgrInterface {

    public BrainInterface spinUpBrain( String brainName ) throws BrainFrameException;
}
