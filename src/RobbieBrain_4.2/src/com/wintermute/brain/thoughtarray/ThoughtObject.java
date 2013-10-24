/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package com.wintermute.brain.thoughtarray;

import com.wintermute.brain.center.Synapse;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.nlp.structure.Phrase;
import java.util.Observable;

/**
 * @author  unknown
 * @version
 */




public class ThoughtObject extends Observable implements ThoughtObjectInterface  {


    private ThoughtObjectKey myKey = null;
    private ThoughtConsumer lastConsumer;
    private ThoughtType thoughtType = ThoughtObjectInterface.ThoughtType.Unknown;
    private ModiferType modifer = ThoughtObjectInterface.ModiferType.Is;
    private Synapse synapse = null;
    private ThoughtPattern parentPattern;
    private String rawThought;
    private boolean isBlocked = false;
    
    private AnswerObject myAnwser = null;

    private Phrase myPhrase;
    //TODO: this need to allow multiple stream membership
    ThoughtStream parentStream;


    // "blocks" until and anwser is given--only in learn mode        
    
  ///////////////////////////////////////
  //operations
/**
 * Creates new ThoughtObject

 * @param creatingConsumer
 * @param type
 * @param mod
 */
   public ThoughtObject( ThoughtConsumer creatingConsumer, ThoughtType type, ModiferType mod ) {
       myKey = ThoughtObjectKey.makeKey(type, mod);
       lastConsumer = creatingConsumer;
       this.thoughtType = type;
       this.modifer = mod;
       synapse = new Synapse( type, mod );      
  }

   public ThoughtObject( ThoughtConsumer creatingConsumer, ThoughtType type, ModiferType mod, Phrase p ) {
       myKey = ThoughtObjectKey.makeKey(type, mod);
       lastConsumer = creatingConsumer;
       this.thoughtType = type;
       this.modifer = mod;
       synapse = new Synapse( type, mod );
       this.myPhrase = p;
  }
/**
 * Does ...
 * 
 * @param item ...
 */
   public ThoughtObject(String item) {
       this.rawThought = item;
  }

   public Phrase getPhrase()
   {
       return myPhrase;
   }

   /**
    *
    * @return
    */
   public ThoughtType getType()
   {
       return thoughtType;
   }

   /**
    *
    * @return
    */
   public String getRawThought()
   {
       return rawThought;
   }
   
   /**
    *
    * @return
    */
   public ModiferType getModifer()
   {
       return modifer;
   }
   
   /**
    *
    * @param newConsumer
    * @param changingThought
    */
   public void Alter( ThoughtConsumer newConsumer, ThoughtObject changingThought )
   {
       this.lastConsumer = newConsumer;
       conformToThought( changingThought );
       this.setChanged();
       notifyObservers(this);
   }
   
   private void conformToThought(ThoughtObject ct  )
   {
       this.thoughtType = ct.thoughtType;
       this.modifer = ct.modifer;
       this.myKey = ct.myKey;
       this.isBlocked = ct.isBlocked;
       this.rawThought = ct.rawThought;
   }
   
   /**
    *
    * @return
    */
   public boolean isWaitingForReply()
   {
       return this.thoughtType.equals(ThoughtType.Question);
   }

   /**
    *
    * @return
    */
   public boolean isQuestion()
   {
       return (modifer.equals(ModiferType.Is)
              || modifer.equals(ModiferType.What)
              || modifer.equals(ModiferType.When)
              || modifer.equals(ModiferType.Who));
   }

   public boolean isAnswer()
   {
       return this.thoughtType.equals(ThoughtType.Answer);
       // return this.myAnwser != null;
   }
   
   /**
    *
    * @return
    */
   public Synapse getSynapse()
   {
       return synapse;
   }
   /**
    *
    * @param str
    */
   public void setRawThought( String str )
   {
       this.rawThought = str;
   }
   
   /**
    *
    * @param isBlocked
    */
   public void block( boolean isBlocked )
   {


       if( this.parentStream != null )
           parentStream.block(isBlocked);
       
       this.isBlocked = isBlocked;
   }

   /**
    *
    * @return
    */
   public ThoughtObjectKey getKey()
   {
       return myKey;
   }

   /**
    *
    * @return
    */
   public boolean isBlocked()
    {
        return this.isBlocked;
    }
   
    @Override
   public String toString()
   {
       StringBuilder sb = new StringBuilder("Source [");
       if( lastConsumer !=  null )
           sb.append(this.lastConsumer.getName());
       else         
           sb.append("Null");
       sb.append("] ");
       sb.append("Raw Thought:\"").append(rawThought);
       sb.append("\"-- Type:(").append(this.thoughtType.toString());
       sb.append(") Modifier:(").append(this.modifer.toString()).append(")");
       return sb.toString();
   }

    /**
     * @return the parentPattern
     */
    public ThoughtPattern getParentPattern() {
        return parentPattern;
    }

    /**
     * @param parentPattern the parentPattern to set
     */
    public void setParentPattern(ThoughtPattern parentPattern) {
        this.parentPattern = parentPattern;
    }

    @Override
    public void setThoughtStream(ThoughtStream ts) {
        this.parentStream = ts;
    }

    @Override
    public ThoughtStream getThoughtStream() {
        return parentStream;
    }

    /**
     * @return the myAnwser
     */
    public AnswerObject getMyAnwser() {
        return myAnwser;
    }

    /**
     * @param myAnwser the myAnwser to set
     */
    public void setMyAnwser(AnswerObject myAnwser) {
        this.myAnwser = myAnwser;
    }
}

