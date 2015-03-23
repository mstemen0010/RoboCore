package com.wintermute.brain.thoughtarray;

import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ModiferType;
import com.wintermute.brain.thoughtarray.ThoughtObjectInterface.ThoughtType;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author mstemen
 */
public class ThoughtObjectKey
{
    ThoughtType typeKeyPart;
    ModiferType modKeyPart;
    static HashSet<ThoughtObjectKey> allKeys = new HashSet<ThoughtObjectKey>();
    private ThoughtObjectKey(ThoughtType type, ModiferType mod)
    {
        this.typeKeyPart = type;
        this.modKeyPart = mod;
    }
    /**
     *
     * @param type
     * @param mod
     * @return
     */
    public static ThoughtObjectKey makeKey(ThoughtType type, ModiferType mod)
    {
        ThoughtObjectKey key = findKey(type, mod);
        if (key == null) {
            key = new ThoughtObjectKey(type, mod);
            allKeys.add(key);
        }
        return key;
    }
    /**
     *
     * @param type
     * @param mod
     * @return
     */
    public static ThoughtObjectKey getKey(ThoughtType type, ModiferType mod)
    {        return makeKey(type, mod);    }
    private static ThoughtObjectKey findKey(ThoughtType type, ModiferType mod)
    {
        Iterator<ThoughtObjectKey> i = allKeys.iterator();
        ThoughtObjectKey k = null;
        while (i.hasNext()) {
            k = i.next();
            if (k.typeKeyPart.equals(type) && k.modKeyPart.equals(mod)) {
                break;
            }
        }
        return k;
    }
}
