/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain.thoughtarray;

/**
 *
 * @author mstemen
 */
public interface ThoughtObjectInterface extends SelfThoughtObjectInterface {


    public void setThoughtStream( ThoughtStream ts );


    public ThoughtStream getThoughtStream();

    /**
     *
     */
    public enum ThoughtType
    {        
        /**
         *
         */
        Unknown,
        /**
         *
         */
        Idle,
        /**
         *
         */
        BrainDead,
        /**
         *
         */
        Locked,
        /**
         *
         */
        Base,
        /**
         *
         */
        Exception,
        /**
         *
         */
        Deep,
        /**
         *
         */
        SelfAware,
        /**
         *
         */
        SelfKnown,
        /**
         *
         */
        SelfLearn,
        /**
         *
         */
        SelfRandom,
        /**
         *
         */
        Inner,
        /**
         *
         */
        Random,
        /**
         *
         */
        Statement,
        /**
         *
         */
        Question,
        /**
         *
         */
        Learn,
        /**
         *
         */
        Memory,

        Answer;
        
    }
    
    /**
     *
     */
    public enum ModiferType
    {
        /**
         *
         */
        Unknown,
        /**
         *
         */
        Exception,
        /**
         *
         */
        Asked,
        /**
         *
         */
        Is,
        /**
         *
         */
        Am,
        /**
         *
         */
        Who,
        /**
         *
         */
        What,
        /**
         *
         */
        Where,
        /**
         *
         */
        When,
        /**
         *
         */
        True,
        /**
         * 
         */
        False,
        /**
         *
         */
        Or,
        /**
         *
         */
        None,
        /**
         *
         */
        All,
        /**
         *
         */
        Fast,
        /**
         *
         */
        Slow,
        /**
         *
         */
        Accept,
        /**
         *
         */
        Resist,
        /**
         *
         */
        Core,
        /**
         *
         */
        Show,
        /**
         *
         */
        Indicate,
        /**
         *
         */
        Clear;
    }
}
