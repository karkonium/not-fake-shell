package test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.History;
import commands.LoadState;
import commands.SaveState;
import commands.Commands;
import tree.Tree;

public class LoadStateTest {
  History history;
  ArrayList<String> locations;
  Tree directory;
  LoadState load;
  ArrayList<String> lists;
  ArrayList<String> pathToFile;
  SaveState state;

  @Before
  public void setUp() {
    directory = null;
    history = null;
    locations = new ArrayList<String>();
    directory = Tree.getTree();
    Commands.setDir(directory);
    pathToFile = new ArrayList<String>();
    lists = new ArrayList<String>();
    load = null;
    state = null;
  }

  @After
  public void tearDown() throws Exception {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }

  @Test
  public void testLoad() throws ClassNotFoundException, IOException {
    history = new History(lists);
    directory.addDirectoryNode("/a");
    pathToFile.add("/Users/clementtran/Desktop/testing123.txt");
    history.addCommand("mkdir a");
    history.addCommand("save /Users/clementtran/Desktop/testing123.txt");
    try {
      state = new SaveState(pathToFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    locations.add("save /Users/clementtran/Desktop/testing123.txt");
    load = new LoadState(locations);

    boolean expected = true;
    boolean actual = (directory != null && history != null);
    assertEquals(expected, actual);
  }

  @Test
  public void testFailLoad() {
    history = new History(lists);
    directory.addDirectoryNode("/a");
    pathToFile.add("/Users/clementtran/Desktop/testing123.txt");
    history.addCommand("mkdir a");
    history.addCommand("save /Users/clementtran/Desktop/testing123.txt");
    try {
      state = new SaveState(pathToFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    locations.add("save /Users/clementtran/Desktop/testing123.txt");
    try {
      load = new LoadState(locations);
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    boolean expected = false;
    boolean actual = (directory == null && history == null);
    assertEquals(expected, actual);
  }
}
