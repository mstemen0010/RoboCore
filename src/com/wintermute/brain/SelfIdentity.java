/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wintermute.brain;

import com.wintermute.brain.thoughtarray.ThoughtObject;
import com.wintermute.nlp.structure.Word;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author mstemen
 */
public class SelfIdentity {
    
    public enum IdentityKey
    {
        Me,
        Name,
        Height,
        Weight,
        Place,
        Birthplace,
        Home,
        Thing,
        Person,
        Color,
        Age;
        
    }
    private HashMap<IdentityKey,String> description = new HashMap<IdentityKey,String>();
    
    public void setIdentity( IdentityKey key, String value )
    {
        description.put( key , value);
    }
    
    public String getIdentity( IdentityKey key )
    {
        return description.get( key );
    }

    public void process( ThoughtObject to )
    {
        
    }
    
    public boolean isSet( IdentityKey keyToTest )
    {
        String value = description.get(keyToTest);
        Word w = new Word( value );
        return value != null && value.length() > 0 && w != null && w.isValid(); // valid may change later conditionallly
    }
    
    public Iterator <IdentityKey> keys()
    {
        return description.keySet().iterator();
    }
    
    public IdentityKey[] getIdentityKeyNames()
    {
        return IdentityKey.values();
    }
    
}
