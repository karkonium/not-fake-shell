package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Commands;
import commands.Ls;
import commands.MkDir;
import commands.TreeCommands;
import tree.Tree;

public class MkDirTest {
  ArrayList<String> lists;
  MkDir myMkDir;
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
  public void testNoDups() {
    lists.add("a");
    lists.add("b");
    lists.add("d");
    myMkDir = new MkDir(lists);
    String output = myMkDir.execute();
    assertEquals("", output);
    ArrayList<String> lsArgs = new ArrayList<String>();
    Ls myLs = new Ls(lsArgs);
    assertEquals("a\nb\nd\n", myLs.execute());
  }

  @Test
  public void testDups() {
    lists.add("a");
    lists.add("b");
    lists.add("a");

    myMkDir = new MkDir(lists);
    String output = myMkDir.execute();
    assertEquals("a: Cannot create Directory\n", output);

    ArrayList<String> lsArgs = new ArrayList<String>();
    Ls myLs = new Ls(lsArgs);
    assertEquals("a\nb\n", myLs.execute());
  }

  @Test
  public void testRedirection() {
    lists.add("a");
    lists.add("a/a");
    lists.add("a/a/a");
    lists.add(">");
    lists.add("b");

    myMkDir = new MkDir(lists);
    String output = myMkDir.execute();
    assertEquals("", output);

    ArrayList<String> lsArgs = new ArrayList<String>();
    lsArgs.add("a");
    lsArgs.add("a/a");
    lsArgs.add("a/a/a");
    Ls myLs = new Ls(lsArgs);
    assertEquals("a: \n" + "a\n" + "a/a: \n" + "a\n" + "a/a/a: \n", myLs.execute());
  }
}
