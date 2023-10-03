package world;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class World{
  private String worldName;
  private static World theWorld;
  
  private World() {
    this.worldName = "";
  }
  
  public static World getWorldInstance() {
    if(theWorld == null) {
      theWorld = new World();
    }
    return theWorld;
  }
  
  //implement readable
  
  
  // TODO setupWorld from file
  public void setupWorld(String fileName) {
    this.worldName = fileName;
  }
  
  public void setWorldName() {
    
  }
  
  public String getWorldName() {
    return worldName;
  }

}