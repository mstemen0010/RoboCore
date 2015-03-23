/*
 * Memory.java
 *
 * Created on October 20, 2001, 3:07 AM
 */
package com.wintermute.brain;

/**
 *
 * @author  unknown
 * @version 
 */
import com.wintermute.brain.center.Synapse;
import com.wintermute.brain.center.BrainCenterInterface;
import com.wintermute.brain.thoughtarray.ThoughtObject;
import com.wintermute.brain.thoughtarray.ThoughtPattern;
import com.wintermute.brain.thoughtarray.ThoughtConsumer;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;
import java.util.HashMap;
import com.wintermute.brain.ObjectNodeNet;

public class Memory extends Object implements BrainCenterInterface, ThoughtConsumer {

    private HashMap<Synapse, ThoughtPattern> patterns = new HashMap<Synapse, ThoughtPattern>();
    private HashMap<Synapse, ThoughtObject> memories = new HashMap<Synapse, ThoughtObject>();
    private BrainInterface brainCortex;
    private boolean answerPending;

    public String getName() {
        return this.getClass().getSimpleName();
    }

    /** Creates new Memory */
    public Memory(BrainInterface brainCortex) {
        // Memory consists of a Hashtable of ObjectNodeNets referenced to ObjectNodeNets
        this.brainCortex = brainCortex;
    }

    public void remember(Synapse synapse, ThoughtPattern thoughtPattern) {
    }

    public void remember(ObjectNodeNet key, ObjectNodeNet newItem) {
    }

    public ThoughtObject process(ThoughtPattern thoughtPattern) {
        return null;
    }

    public ThoughtObject process(ThoughtObject thought) {
        Synapse s = thought.getSynapse();
        ThoughtObject t = memories.get(s);
        if (t == null) {
            t = new ThoughtObject(this, ThoughtType.Unknown, ModiferType.Unknown);
        }

        return t;
    }

    public void send(ThoughtObject thought) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void learn(ThoughtObject thought) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void learn(ThoughtPattern pattern) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAnswerPending() {
        return answerPending;
    }

    void answerPending(boolean flag) {
        answerPending = flag;
    }
}
