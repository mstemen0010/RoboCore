/*
 * ObjectNodeNet.java
 *
 * Created on October 20, 2001, 2:35 AM
 */

package com.wintermute.brain;

import java.util.*;
/**
 *
 * @author  unknown
 * @version 
 */
public class ObjectNodeNet extends Object {

    /** Creates new ObjectNodeNet */
    private Hashtable nodeNet = new Hashtable();
    private String netName = null;
    public ObjectNodeNet(String newNetName) {
        netName = new String(newNetName);               
    }
    public void addNode(ObjectNode nodeToAdd) {
        String key = nodeToAdd.name();
        nodeNet.put(key, nodeToAdd);
    }
    public void delNode(ObjectNode nodeToDel) {
        String key = nodeToDel.name();
        if (nodeNet.containsKey(key)) {
            nodeNet.remove(key);
        }
    }
    public void relateNode(ObjectNode nodeToRelate, String netName) {
        String key = netName;
        if (netName.equals(key)) {
            addNode(nodeToRelate);
        }            
    }

}
