/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain.thoughtarray;

import com.wintermute.brain.BrainInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mstemen
 */
public class ThoughtMonitor implements Runnable {
    
    private BrainInterface myBrain = null;

    /**
     *
     * @param myBrain
     */
    public ThoughtMonitor(BrainInterface myBrain )
    {
        this.myBrain = myBrain;

    }
    /**
     *
     * @param to
     */
    public void registerThought( ThoughtObject to )
    {
        
    }
    
    /**
     *
     * @param orphanedThought
     */
    public void assignOrphan( ThoughtObject orphanedThought )
    {
        
    }

    @Override
    public void run()
    {
       while(myBrain.isAlive(myBrain))
       {
            try {
                Thread.sleep(50000);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(ThoughtMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }

       }
    }
    
    

}
