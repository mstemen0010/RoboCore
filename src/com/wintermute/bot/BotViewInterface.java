/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.bot;

import com.wintermute.brain.thoughtarray.ThoughtObject;

/**
 *
 * @author mstemen
 */
public interface BotViewInterface 
{
    /**
     *
     */
    public enum BotStatusColor
    {
        /**
         *
         */
        Unknown,
        /**
         *
         */
        Grey,
        /**
         *
         */
        Green,
        /**
         *
         */
        Yellow,
        /**
         *
         */
        Red,
        /**
         * 
         */
        Purple;
    }
    
       /**
        *
        */
       public enum BotStatus
    {
        /**
         *
         */
        Unknown,
        /**
         *
         */
        Loop,
        /**
         *
         */
        Think,
        /**
         *
         */
        Learn,
        /**
         *
         */
        Question;
    }

    /**
     *
     * @param thought
     */
    public void say( ThoughtObject thought );
    
    /**
     *
     * @param msg
     */
    public void logMessage( String msg );

    /**
     *
     */
    public void statusGreen();

    /**
     *
     */
    public void statusGrey();

    /**
     *
     */
    public void statusRed();
    
    /**
     *
     * @param thought
     */
    public void consciousThought( ThoughtObject thought );
    
    /**
     *
     * @param thought
     */
    public void subConsciousThought( ThoughtObject thought );

    /**
     *
     * @param id
     */
    public void setBotId(String id );

    /**
     *
     * @param status
     * @param color
     */
    public void setStatusIcon( BotStatus status, BotStatusColor color );

    /**
     *
     * @param status
     * @param flag
     */
    public void setBotStatus( BotStatus status, boolean flag );

    /**
     *
     * @param to
     */
    public void showThought( ThoughtObject to);

    /**
     *
     */
    public void indicateQuestion();

}
