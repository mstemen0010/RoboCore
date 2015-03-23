/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain;

import com.wintermute.brain.frame.BrainFrame;
import com.wintermute.brain.frame.BrainFrameException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mstemen
 */
public class BrainMgr implements BrainMgrInterface {

    private static BrainMgr inst;
    
    private BrainMgr(){};
    
    public static BrainMgrInterface getInstance()
    {
        if( inst ==  null )
        {
            inst = new BrainMgr();
        }        
        return inst;
    }
    public BrainInterface spinUpBrain(String brainName) throws BrainFrameException
    {
        // brainName is assumed to be a class, lets find out...
        Class brainClass = null;
        BrainInterface bi = null;
        try {
            brainClass = Class.forName( "com.wintermute.brain." + brainName);
            bi = BrainFrame.getInstance().spinUpBrain("com.wintermute.brain." + brainName );
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(BrainMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return bi;
    }
    
}
