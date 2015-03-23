/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package com.wintermute.brain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @author  unknown
 * @version
 */

public class Profile extends Object {
  ///////////////////////////////////////
  //attributes
/**
 * Creates new Profile

 */

  boolean gender =  true;
/**
 * Represents ...

 */

  String name =  null;
/**
 * Represents ...

 */

  String nick =  null;
/**
 * Represents ...

 */

  String email =  null;
/**
 * Represents ...

 */

  String username =  null;
/**
 * Represents ...

 */

  String location =  null;
/**
 * Represents ...

 */

  String age =  null;
/**
 * Represents ...

 */

  Properties profile =  new Properties();
  public void setGender(boolean _gender){ gender = _gender; }
  public boolean getGender(){ return gender; }

  public void setName(String _name){ name = _name; }
  public String getName(){ return name; }

  public void setNick(String _nick){ nick = _nick; }
  public String getNick(){ return nick; }

  public void setEmail(String _email){ email = _email; }
  public String getEmail(){ return email; }

  public void setUsername(String _username){ username = _username; }
  public String getUsername(){ return username; }

  public void setLocation(String _location){ location = _location; }
  public String getLocation(){ return location; }

  public void setAge(String _age){ age = _age; }
  public String getAge(){ return age; }

  public void setProfile(Properties _profile){ profile = _profile; }
  public Properties getProfile(){ return profile; }



  ///////////////////////////////////////
  //operations
/**
 * Does ...
 * 
 * @param defaultProfile ...
 */
   public Profile(String defaultProfile) {
                loadProfile(defaultProfile);
  }

/**
 * Does ...
 * 
 * @return A void with ...
 * @param nameToLoad ...
 */
  public void loadProfile(String nameToLoad) {
                String fileName = nameToLoad + ".prp";
                try {                    
                    profile.load(new FileInputStream(new File("F:\\dev\\com\\wintermute\\net\\bots", fileName)));            
                }
                catch (java.io.IOException e) {
                    System.out.println("Got Exception loading profile: " + e.getMessage());
                }
  }

/**
 * Does ...
 * 
 * @return A void with ...
 * @param nameToSave ...
 */
  public void saveProfile(String nameToSave) {
                String fileName = nameToSave + ".prp";
                try {                            
                    profile.save(new FileOutputStream(new File("F:\\dev\\com\\wintermute\\net\\bots", fileName)), null);          
                }
                catch (java.io.IOException e) {
                    System.out.println("Got Exception loading profile: " + e.getMessage());
                }
  }

}

