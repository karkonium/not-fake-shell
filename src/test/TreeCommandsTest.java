package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Commands;
import commands.MkDir;
import commands.TreeCommands;
import tree.Tree;


public class TreeCommandsTest {
  ArrayList<String> lists;
  TreeCommands myTree;
  Tree directory;
  MkDir myMkDir;
  ArrayList<String> listForMkDir;
  
  @Before
  public void setUp() throws Exception {
    directory = Tree.getTree();
    Commands.setDir(directory);
    lists= new  ArrayList<String>();
    listForMkDir= new  ArrayList<String>();
  }
  
  @After
  public void tearDown() throws Exception
  {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); //setting the ref parameter to null
  }
  

  @Test
  public void testExecute() {
    listForMkDir.add("a");
    listForMkDir.add("b");
    listForMkDir.add("b/c");
    myMkDir = new MkDir(listForMkDir);
    myMkDir.execute();
    myTree = new TreeCommands(lists);
    String actual = myTree.execute();
    assertEquals("/" + System.lineSeparator() + "\t" + "a\n" + "\t" + "b\n"
    + "\t" +"\t" + "c\n", actual);
  }
  
  @Test
  public void testexecuteTreeMethod() {
    lists.add("DON'T WANT THIS");
    myTree = new TreeCommands(lists);
    String output = myTree.execute();
    assertEquals("Invalid number of arguments", output);
  }

}
