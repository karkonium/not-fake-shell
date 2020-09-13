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
import tree.Tree;

public class LsTest {
  ArrayList<String> lists;
  Ls myLs;
  Tree directory;
  MkDir myMkdir;
  ArrayList<String> tempList;

  @Before
  public void setUp() throws Exception {
    directory = Tree.getTree();
    Commands.setDir(directory);
    lists = new ArrayList<String>();
    tempList = new ArrayList<String>();
  }

  @After
  public void tearDown() throws Exception {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }

  @Test
  public void testNoArgs() {
    tempList.add("a");
    tempList.add("b");
    tempList.add("c");
    myMkdir = new MkDir(tempList);
    myMkdir.execute();
    myLs = new Ls(lists);
    String output = myLs.execute();
    assertEquals("a\nb\nc\n", output);
  }

  @Test
  public void testArgs() {
    tempList.add("a");
    tempList.add("b");
    tempList.add("c");
    tempList.add("a/b");
    myMkdir = new MkDir(tempList);
    myMkdir.execute();
    lists.add("a");
    myLs = new Ls(lists);
    String output = myLs.execute();
    assertEquals("a: \n" + "b\n", myLs.getOutput());
  }

  @Test
  public void testRedirection() {
    tempList.add("a");
    tempList.add("b");
    tempList.add("c");
    myMkdir = new MkDir(tempList);
    myMkdir.execute();
    lists.add(">");
    lists.add("file");
    myLs = new Ls(lists);
    String output = myLs.execute();
    assertEquals("", output);
  }

  @Test
  public void testRecursionNoPath() {
    tempList.add("a");
    tempList.add("b");
    tempList.add("b/hey");
    tempList.add("b/wow");
    myMkdir = new MkDir(tempList);
    myMkdir.execute();
    lists.add("-R");
    myLs = new Ls(lists);
    String output = myLs.execute();
    assertEquals("/a: \n/b: \nhey\nwow\n/b/hey: \n/b/wow: \n", output);
  }

  @Test
  public void testRecursionPath() {
    tempList.add("a");
    tempList.add("b");
    tempList.add("b/hey");
    tempList.add("b/wow");
    myMkdir = new MkDir(tempList);
    myMkdir.execute();
    lists.add("-R");
    myLs = new Ls(lists);
    String output = myLs.execute();
    assertEquals("/a: \n/b: \nhey\nwow\n/b/hey: \n/b/wow: \n", output);
  }

}
