/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain.cortex;

import com.wintermute.brain.thoughtarray.ThoughtObject;
import java.util.Observer;

/**
 *
 * @author mstemen
 */
public interface BrainCortexListener extends Observer {

    public void process(ThoughtObject to);

}
