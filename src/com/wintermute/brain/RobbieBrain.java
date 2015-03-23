/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain;

import com.wintermute.brain.frame.BrainFrame;

/**
 *
 * @author mstemen
 */
public class RobbieBrain extends Brain implements BrainInterface
{

    @Override
    public void setBrainFrame(BrainFrame myFrame)
    {
        super.myFrame = myFrame;
    }

}
