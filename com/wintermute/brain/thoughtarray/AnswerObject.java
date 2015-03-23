/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wintermute.brain.thoughtarray;

/**
 *
 * @author mstemen
 */
public class AnswerObject {

    public enum AnswerType {

        Name,
        Thing;
    }
    
    ThoughtObject parentThoughtObject = null;
    ThoughtConsumer myConsumer = null;
    boolean outStanding = false;
    boolean isSatified = false;

    /**
     *
     * @param myTo
     */
    public AnswerObject(ThoughtObject myTo) {
        this.parentThoughtObject = myTo;
    }

    /**
     *
     * @param to
     */
    public void assignToObject(ThoughtObject to) {
        this.parentThoughtObject = to;
        to.setMyAnwser(this);
    }

    public ThoughtObject getThoughtObject() {
        return parentThoughtObject;
    }

    /**
     *
     * @return
     */
    public boolean isOutStanding() {
        return outStanding;
    }

    /**
     *
     * @return
     */
    public boolean isSatisfied() {
        return isSatified;
    }
}
