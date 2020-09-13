package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Commands;
import commands.Ls;
import commands.Man;
import tree.Tree;

public class ManTest {
  ArrayList<String> lists;
  Man myMan;
  Ls myLs;
  Tree directory;

  @Before
  public void setUp() throws Exception {
    directory = Tree.getTree();
    Commands.setDir(directory);
    lists = new ArrayList<String>();
  }

  @After
  public void tearDown() throws Exception {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }

  @Test
  public void testExecute() {
    lists.add("history");
    myMan = new Man(lists);
    String actualOutput = myMan.execute();
    assertEquals("NAME: history\nSYNOPSIS: history [number]\nDESCRIPTION: This command will"
        + " print out recent commands, one command per line.", actualOutput);
  }

  @Test
  public void testExecuteRedirection() {
    lists.add("echo");
    lists.add(">");
    lists.add("file");
    myMan = new Man(lists);
    String actualOutput = myMan.execute();
    ArrayList<String> temp = new ArrayList<String>();
    Ls myLs = new Ls(temp);
    String lsOut = myLs.execute();
    assertEquals("", actualOutput);
    assertEquals("file\n", lsOut);
  }

  @Test
  public void testExecuteInvalid() {
    lists.add("doesnotexist");
    myMan = new Man(lists);
    String actualOutput = myMan.execute();
    ArrayList<String> temp = new ArrayList<String>();
    assertEquals("doesnotexist is not an existing command", actualOutput);
  }


}
