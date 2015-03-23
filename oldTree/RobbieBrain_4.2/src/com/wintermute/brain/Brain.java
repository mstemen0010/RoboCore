/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package com.wintermute.brain;

import com.wintermute.bot.behavior.BehaviorEngine;
import com.wintermute.brain.frame.BrainFrame;
import com.wintermute.brain.center.SelfCenter;
import com.wintermute.brain.center.SpeechCenter;
import com.wintermute.brain.center.ListeningCenter;
import com.wintermute.brain.center.CognitiveCenter;
import com.wintermute.brain.cortex.BrainCortex;
import com.wintermute.brain.frame.BrainFrameInterface;
import com.wintermute.brain.thoughtarray.AnswerObject;
import com.wintermute.brain.thoughtarray.ThoughtObject;
import com.wintermute.brain.thoughtarray.ThoughtStream;
import com.wintermute.brain.thoughtarray.ThoughtConsumer;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author  unknown
 * @version
 */
public class Brain implements Observer, BrainInterface, Runnable, ThoughtConsumer
{

    BrainFrame myFrame = null;
    BrainCortex myCortex = null;
    ThoughtObject lastThought = null;
    ThoughtObject currentThought = null;
    AnswerObject currentAnswer = null;

    BehaviorEngine behavior = null;

    public BrainInterface getBrainInterface()
    {
        return this;
    }
    
    public String getName()
    {
        return Brain.class.getSimpleName();
    }

    public Brain getBrain()
    {
        return this;
    }

    public boolean isAlive(BrainInterface bi)
    {
        return myFrame.isAlive(bi);
    }

    public void setBrainFrame(BrainFrame bf)
    {
        myFrame = bf;
    }

    public BrainFrameInterface getBrainFrame()
    {
      return myFrame;
    }

    public void showThought(ThoughtObject to)
    {
        myFrame.showThought(to);

    }

    public void addObserver(Observer obs)
    {        
        this.myCortex.addObserver(obs);
    }

    @Override
    public BrainCortex getBrainCortex() {
        return this.myCortex;
    }

    public enum BrainMode
    {

        Unknown,
        Learn,
        Random,
        Teach;
    }

    public enum RandomThought
    {

        Undefined,
        Make,
        Alter,
        Past, // past memory
        State; // state something that is known

        public RandomThought get()
        {
            Random r = new Random(System.currentTimeMillis());
            int nrti = r.nextInt(RandomThought.values().length - 1); // we do not want to return Undefined            
            return RandomThought.values()[nrti + 1]; // Undefined + 1                                    
        }
    }
    ///////////////////////////////////////
    //attributes
    /**
     * Represents ...
    
     */
    private CognitiveCenter cognitiveCenter;
    /**
     * Represents ...
    
     */
    private SpeechCenter speechCenter;
    /**
     * Represents ...
    
     */
    private SelfCenter selfCenter;
    /**
     * Represents ...
    
     */
    private ListeningCenter listeningCenter;
    // single listener for now
    private BrainListenerInterface brainListener;
    private Memory brainMemory;
    private boolean alive = false;
    private boolean aware = false;
    private int sleepTime = 200;
    private int processTime;  // note this is a package level access, all brain functions can alter this...
    private Memory memory;
    private BrainMode currentBrainMode;
    private BrainMode previousBrainMode;
    private BrainFrame myBrainFrame = null;
    // sets a flag that does not allow the brain to automatically switch mode
    private boolean isForced = false;
    private boolean isBlocked = false;
    private boolean wasBlocked = false;
    ThoughtStream mainStream = null;
    Thread mainStreamThread = null;
    String id = "Brain::";
    String processingThought = id + "Thinking...";

    public Brain()
    {
        alive = true;
        lastThought = new ThoughtObject(null);
        currentThought = new ThoughtObject(null);
        this.brainMemory = new Memory(this);
        this.speechCenter = new SpeechCenter(this);
        this.listeningCenter = new ListeningCenter(this);
        this.selfCenter = new SelfCenter(this);
        this.cognitiveCenter = new CognitiveCenter(this);

        System.out.println("Brain inited centers");
        // start the main thought stream   
        this.myCortex = new BrainCortex(this);
        this.mainStream = new ThoughtStream(myCortex);

	this.behavior = new BehaviorEngine();
    }

    public void setViewObserver(Observer obs )
    {
        this.myCortex.addObserver(obs);
    }

    public void send(ThoughtObject thought)
    {
        if (brainListener != null) {
            brainListener.hear(thought);
        }
    }

    public boolean tryAware()
    {
        return becomeAware();
    }
    
    public ThoughtObject think()
    {
        if( mainStream != null && mainStream.isStreamBlocked())
        {
            return null;
        }
        ThoughtObject to = new ThoughtObject( this, ThoughtType.Deep, ModiferType.Indicate);
        to.setRawThought("Brain::think: Starting Deep Thought...");
        this.myCortex.say(to);
        return this.haveThought();
    }

    public boolean isAlive()
    {
        return alive;
    }

    public boolean isLearn()
    {
        return (currentBrainMode == BrainMode.Learn);
    }

    @Override
    public void questionAskedExpectReply(AnswerObject ao )
    {
        currentBrainMode = BrainMode.Learn;
        // indicate learn mode...
        ThoughtObject qo = new ThoughtObject(this, ThoughtType.Learn, ModiferType.Indicate);
        myCortex.showThought(qo);
        
        this.currentAnswer = ao;
        this.think(ao.getThoughtObject());
                // myCortex.questionAskedExpectReply(currentAnswer);
    }

    public void forceLearn()
    {
        isForced = true;
        previousBrainMode = currentBrainMode;
        currentBrainMode = BrainMode.Learn;

    }

    public void setFree()
    {
        isForced = false;
        currentBrainMode = previousBrainMode;
    }

    private boolean becomeAware()
    {
        // start the thought chain
        // ThoughtObject initialThought = new ThoughtObject(this, ThoughtType.SelfAware, ModiferType.Is);
        ThoughtObject initialThought = getThought();

        if (mainStream == null) {
            mainStream = new ThoughtStream(myCortex);
            myCortex.addObserver(this);
            mainStreamThread = new Thread(mainStream, mainStream.getName());
            mainStream.becomeAlive();
            mainStream.becomeAware(initialThought);
            mainStreamThread.start();
            this.alive = true;
        }

        return this.alive && this.aware;

    }

    private ThoughtObject getThought()
    {
        ThoughtObject isAliveThought = null;
        ThoughtObject newThought = null;
        // the first though ever would be, who am I 
        // ask the cognitive center;
        if( alive )
        {
        isAliveThought = new ThoughtObject(this, ThoughtType.SelfAware, ModiferType.Is);
        ThoughtObject t = selfCenter.process(isAliveThought);
        // if( t.)
        if( t.getModifer() == ModiferType.Am && t.getType() == ThoughtType.SelfAware )
        {
            newThought = t;
        }
        }
        return newThought;
    }

    public CognitiveCenter getCognitiveCenter()
    {
        return cognitiveCenter;
    }

    public SpeechCenter getSpeechCenter()
    {
        return speechCenter;
    }

    public SelfCenter getSelfCenter()
    {
        return selfCenter;
    }

    public Memory getMemory()
    {
        return memory;
    }
    ///////////////////////////////////////
    //operations

    /**
     * Does ...
     * 
     * @return A value 
     */
    /**
     * Does ...
     * 
     * @param input ...
     * @return A String with ...
     */
    /**
     * Does ...
     * 
     * @param args ...
     * @return A void with ...
     */
    public void main(String[] args)
    {
    }

    /**
     * Does ...
     * 
     * @return A Object with ...
     */
    protected Object clone()
    {
        return null;
    }

    /**
     * Does ...
     * 
     * @return A void with ...
     */
    protected void finalize()
    {
    }

    /**
     * Does ...
     * 
     * @param input ...
     * @return A void with ...
     */
    public void look(String input)
    {
    }

    /**
     * Does ...
     * 
     * @param input ...
     * @return A void with ...
     */
    public void say(String input)
    {

        ThoughtObject t = new ThoughtObject(this, ThoughtType.SelfAware, ModiferType.Unknown);
        t.setRawThought(input);
        t.block(true);
        send(t);

    }

    /**
     * Does ...
     * 
     * @param input ...
     * @return A void with ...
     */
    public void am(String input)
    {
    }

    /**
     * Does ...
     * 
     * @param inNode ...
     * @return A void with ...
     */
    public boolean isReady()
    {
        return alive;
    }

    public void relate(ObjectNode inNode)
    {
    }

    public void typeThought(ThoughtObject to)
    {
        if(to == null )
        {
            System.out.println("Brain::typeThought: Thought was null!!");
            return;
        }
        System.out.println("Brain::typeThought: Thought is: " + to.toString());
        ModiferType tf = to.getModifer();

        switch (tf) {
            
            // questions that block thought...
        case Is:
        case Who:
        case What:
        case Where:


            break;



        }
    }

    private ThoughtObject haveThought()
    {
        if( mainStream != null && mainStream.isStreamBlocked())
        {
            return null;
        }
        System.out.println(this.processingThought);
        innerThought(this.processingThought);
        ThoughtObject newThought = new ThoughtObject(this, ThoughtType.Unknown, ModiferType.Unknown);
        if(alive && mainStream != null )
        {
            newThought = mainStream.getCurrentThought();
            lastThought = currentThought;
            currentThought = newThought;
            if( lastThought != null && lastThought.equals(currentThought))
            {
                // brain is "looping"
                System.out.println("Last thought is equal to current--brain is looped");
//                ThoughtObject looped = new ThoughtObject(this, ThoughtType.Locked, ModiferType.Indicate);
//                looped.setRawThought("ThoughtObject::haveThought: last thought was equal to current, brain is looped");

                this.mainStream.block(true);
//                this.myCortex.say(looped);
            }
            else if(lastThought == null )
            {
                System.out.println("Last thought is null!!--brain is dead");
                ThoughtObject brainDead = new ThoughtObject(this, ThoughtType.BrainDead, ModiferType.Indicate);
                brainDead.setRawThought("ThoughtObject::haveThought: Brain Death !!!!");
                this.myCortex.say(brainDead);
            }
            else
            {
                typeThought(newThought);
            }
        }

        return newThought;
    }

    public void addListener(BrainListenerInterface listener)
    {
        brainListener = listener;
    }

    public ThoughtObject remember(
            ThoughtObject thoughtObject)
    {
        return this.brainMemory.process(thoughtObject);
    }

    private void innerThought(String t)
    {
        if (brainListener != null) {
            brainListener.hear(t);
        }
    }

    public ThoughtObject think(String input)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void run()
    {
        while (alive) {
            if (!mainStream.isStreamBlocked()) {
                // were we blocked last cycle?
                if (wasBlocked) {
                    wasBlocked = false;
                    // something may have changed.. notify
                    if( SelfCenter.isAware())
                        continue;
//                    this.setChanged();
//                    this.notifyAll();
                }
                else {
//                    this.clearChanged();

                }
            }
            try {
                //
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(Brain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // this are synced in the base class, but could unsync in a derived class.. i.e. to simulate odd behavior
    // this should be concidered throught out the base class
    synchronized public int getProcessTime()
    {
        return this.processTime;
    }

    synchronized public void setProcessTime(int newTime)
    {
        this.processTime = newTime;
    }

    public void update(Observable o, Object arg)
    {
        // see is there is a new thought object or, should we just check out the thought stream
        if (arg != null) {
        }
        else {
        }
    }

    public boolean isStreamBlocked()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBlocked(boolean blocked)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean wasBlocked()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void say(ThoughtObject to)
    {
        myCortex.say(to);
        say(to.toString());
    }

    public ThoughtObject think(ThoughtObject to)
    {
        // pass the thought through the brain centers
        ThoughtObject nto = null;
        if( to.isAnswer())
        {

            // test each center to see if they are waiting for an answer
            if( selfCenter.isAnswerPending())
            {
                nto = selfCenter.process(to);

                if (nto == to) // selfCenter did not know how to process it
                {
                }

            }

        }
        return nto;
    }
}

