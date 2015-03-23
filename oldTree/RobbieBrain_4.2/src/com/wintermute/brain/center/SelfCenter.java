/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package com.wintermute.brain.center;

import com.wintermute.brain.*;
import com.wintermute.brain.thoughtarray.ThoughtConsumer;
import com.wintermute.brain.SelfIdentity.IdentityKey;
import com.wintermute.brain.thoughtarray.ThoughtException;
import com.wintermute.brain.thoughtarray.ThoughtObject;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;
import com.wintermute.brain.thoughtarray.ThoughtPattern;
import com.wintermute.nlp.structure.Word;
import java.util.*;

/**
 * @author  unknown
 * @version
 */
public class SelfCenter extends Object implements BrainCenterInterface, ThoughtConsumer
{
    ///////////////////////////////////////
    //attributes

    /**
     * Creates new SelfCenter
    
     */
    private BrainInterface brainCortex;
    private static SelfIdentity identity = new SelfIdentity();
    /**
     * Represents ...
    
     */
    private Word name = null;
    private ArrayList<Word> names = new ArrayList<Word>();
    private String trueSelf = null;
    private ThoughtObject selfAware = new ThoughtObject(this, ThoughtType.SelfAware, ModiferType.Is);
    private ThoughtObject none = new ThoughtObject(this, ThoughtType.Unknown, ModiferType.Unknown);
    private ThoughtObject answer = null;

    private boolean answerPending = false;

    /**
     *
     * @return
     */
    @Override
    public String getName()
    {
        return this.getClass().getSimpleName();
    }


    /**
     *
     * @param _identnity
     */
    public void setIdentnity(SelfIdentity _identnity)
    {
        identity = _identnity;
    }

   

    /**
     *
     * @return
     */
    public SelfIdentity getIdentnity()
    {
        return identity;
    }

    /**
     *
     * @param _trueSelf
     */
    public void setTrueSelf(String _trueSelf)
    {
        trueSelf = _trueSelf;
    }

    /**
     *
     * @return
     */
    public String getTrueSelf()
    {
        return trueSelf;
    }

    ///////////////////////////////////////
    //operations
    /**
     * Does ...
     * 
     * @param brainCortex
     */
    public SelfCenter(BrainInterface brainCortex)
    {
        this.brainCortex = brainCortex;
    }

    /**
     * Does ...
     * 
     * @param thought
     * @return A ThoughtObject with ...
     */
    @Override
    public ThoughtObject process(ThoughtObject thought)
    {
        ThoughtObject newThought = null;
        if (thought.getType().equals(ThoughtType.SelfAware) && thought.getModifer().equals(ModiferType.Is)) {
            ThoughtObject thinkingInd = new ThoughtObject( this, ThoughtType.SelfLearn, ModiferType.Indicate);
            this.brainCortex.showThought(thinkingInd);
            Synapse s = thought.getSynapse();
            s.myModType = ModiferType.Core;
            s.myType = ThoughtType.Memory;
            newThought = brainCortex.remember(thought);
            if (!isAware()) {
                // brainCortex.
                newThought = new ThoughtObject(this, ThoughtType.Question, ModiferType.What);
                newThought.setRawThought("I don't know nothing about nothing");
                newThought.block(true);
                brainCortex.say(newThought);
                ThoughtObject showQuery = new ThoughtObject( this, ThoughtType.Question, ModiferType.Indicate);
                showQuery.setRawThought("SelfCenter::process: Setting the Question Indicator to true");
                brainCortex.showThought(showQuery);
                newThought.setRawThought("Do you know what my name is?");
                brainCortex.say(newThought);
                this.answerPending = true;
                
            }
            else {
                newThought = new ThoughtObject(this, ThoughtType.SelfAware, ModiferType.Am);
                thought = newThought;
                if (!identity.isSet(IdentityKey.Person) && !identity.isSet(IdentityKey.Thing)) {
                    brainCortex.say("I don't know who or what I am...");
                }
                else if (!identity.isSet(IdentityKey.Name)) {
                    brainCortex.say("Who am I?");
                }
                else if (!identity.isSet(IdentityKey.Place)) {
                    brainCortex.say("Where am I?");
                }

            }

        }
        else {
            return thought;
        }
        extendSelf(thought);
        return newThought;
    }

    /**
     * Does ...
     * 
     * @return A void with ...
     * @param newIdentity ...
     */
    private void extendSelf(ThoughtObject newIdentity)
    {
        identity.process(newIdentity);
    }

    /**
     * Does ...
     * 
     * @return A boolean with ...
     */
    public boolean isI()
    {
        if (isAware()) {
            return true;
        }
        if (brainCortex != null) {
            return false;
        }
        brainCortex.say("I don't know nothing about nothing");

        return false;
    }

    private boolean is(ThoughtObject t)
    {
        boolean isa = false;
        if (t.getSynapse().equals(this.none.getSynapse())) {
        }

        return isa;
    }

    /**
     *
     * @return
     */
    public boolean hasName()
    {
        return name != null && name.isValid();
    }

    /**
     *
     * @return
     */
    public Word myName()
    {
        return name;
    }

    private void idSelf()
    {

        if (name != null && name.isValid()) {
            brainCortex.say("My name is " + name.toString());
        }
        if (names != null && !names.isEmpty()) {
            Iterator<Word> i = names.iterator();
            brainCortex.say("But sometimes people call me: " + names.toString());
            while (i.hasNext()) {
                brainCortex.say(i.next().toString() + "or, ");
            }
        }
        else {
            brainCortex.say("Me?, I don't have a name");
        }
    }

    private void sayName()
    {
        brainCortex.say("My name is " + name.toString());
    }

    /**
     *
     * @param newName
     * @throws ThoughtException
     */
    public void name(Word newName) throws ThoughtException
    {
        if (newName == null) {
            throw new ThoughtException(ThoughtType.SelfAware, ModiferType.Resist, "NULL? - What kinda name is that to have");
        }
        if (name == null && newName.isValid()) {
            name = newName;
        }
        else if (name != null && newName.isValid()) {
            names.add(name);
        }
        else if (!newName.isValid()) {
            throw new ThoughtException(ThoughtType.SelfAware, ModiferType.Resist, new String(newName.toString() + "? That is a strange name to have!!"));
        }
        else {
        }

    }

    /**
     *
     * @return
     */
    public static boolean isAware()
    {
        return identity.isSet(IdentityKey.Person) || identity.isSet(IdentityKey.Thing);
    }

//    public boolean am(String msg) {
//        Phrase p = new Phrase(msg);
//        boolean am = false;
//        Iterator<String> i = identity.
//        Enumeration e = identity.keys();
//        while (e.hasMoreElements()) {
//            String t = (String) e.nextElement();
//            if (p.contains(t.toLowerCase())) {
//                am = true;
//                break;
//            }
//        }
//        return am;
//    }
    /**
     *
     * @param thought
     */
    @Override
    public void send(ThoughtObject thought)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param thought
     */
    @Override
    public void learn(ThoughtObject thought)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param pattern
     */
    @Override
    public void learn(ThoughtPattern pattern)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAnswerPending() {
        return answerPending;
    }

    void answerPending(boolean flag)
    {
        answerPending = flag;
    }
}

