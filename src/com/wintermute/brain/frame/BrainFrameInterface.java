/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain.frame;

import com.wintermute.bot.BotViewInterface;
import com.wintermute.brain.*;

/**
 *
 * @author mstemen
 */
public interface BrainFrameInterface {
    /**
     *
     * @param brainName
     * @return
     * @throws BrainFrameException
     */
    public BrainInterface spinUpBrain(String brainName) throws BrainFrameException;

    /**
     *
     * @param botView
     */
    public void setBotView(BotViewInterface botView);

}
