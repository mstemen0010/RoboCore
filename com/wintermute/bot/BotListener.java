/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.bot;

import java.util.Observer;

/**
 *
 * @author mstemen
 */
public interface BotListener extends Observer {

    /**
     *
     */
    public enum BrainStatusState
    {
        /**
         *
         */
        Unknown,
        /**
         * 
         */
        On,
        /**
         *
         */
        Off;
    }

    /**
     *
     */
    public enum BrainStatus
    {
        /**
         *
         */
        Unknown,
        /**
         *
         */
        Alive,
        /**
         *
         */
        Locked,
        /**
         *
         */
        Deep,
        /**
         *
         */
        Conscious,
        /**
         *
         */
        Subconscious,
        /**
         *
         */
        Learn,
        /**
         *
         */
        Question,
        /**
         *
         */
        Curious,
        /**
         *
         */
        Mad,
        /**
         *
         */
        Confused,
        /**
         *
         */
        Happy,
        /**
         *
         */
        Sad,
        /**
         *
         */
        Angery,
        /**
         *
         */
        Dead;
    }

    /**
     *
     * @param status
     * @param state
     */
    public void showBrainStatus(BrainStatus status, BrainStatusState state);


}

