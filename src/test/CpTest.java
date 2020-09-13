package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Commands;
import commands.Cp;
import commands.Echo;
import commands.Ls;
import commands.MkDir;
import display.Display;
import tree.Tree;

public class CpTest {

  ArrayList<String> lists;
  Cp myCp;
  Tree directory;
  MkDir myMkDir;
  ArrayList<String> mkdirList;
  Ls myLs;
  ArrayList<String> lsList;
  Echo myEcho;
  ArrayList<String> echoList;

  @Before
  public void setUp() throws Exception {
    directory = Tree.getTree();
    Commands.setDir(directory);
    lists = new ArrayList<String>();
    mkdirList = new ArrayList<String>();
    lsList = new ArrayList<String>();
    echoList = new ArrayList<String>();
    Display.printStop();
  }

  @After
  public void tearDown() throws Exception {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
    lists = null;
    mkdirList = null;
    lsList = null;
    echoList = null;
  }

  @Test
  public void testCpDirectory() {
    mkdirList.add("a");
    mkdirList.add("b");
    mkdirList.add("b/hey");
    mkdirList.add("b/wow");
    myMkDir = new MkDir(mkdirList);
    myMkDir.execute(); // creating directories
    lists.add("b");
    lists.add("a");
    myCp = new Cp(lists);
    String output = myCp.execute(); // copying b contents into a
    assertEquals("", output); // checking no output from cp
    lsList.add("a");
    myLs = new Ls(lsList);
    String lsOutput = myLs.execute();
    assertEquals("a: \nhey\nwow\n", lsOutput); // checking contents of a
  }

  @Test
  public void testDNEDir() {
    mkdirList.add("a");
    myMkDir = new MkDir(mkdirList);
    myMkDir.execute(); // creating directories
    lists.add("b");
    lists.add("a");
    myCp = new Cp(lists);
    String output = myCp.execute(); // copying b contents into a
    assertEquals("", output); // checking no output from cp
    lsList.add("a");
    myLs = new Ls(lsList);
    String lsOutput = myLs.execute();
    assertEquals("a: \n", lsOutput); // checking contents of a
  }

  @Test
  public void testCpToFile() {
    mkdirList.add("a");
    mkdirList.add("a/b");
    myMkDir = new MkDir(mkdirList);
    myMkDir.execute(); // creating directories
    echoList.add("hello");
    echoList.add(">");
    echoList.add("file");
    myEcho = new Echo(echoList);
    myEcho.execute();
    lists.add("a");
    lists.add("file");
    myCp = new Cp(lists);
    lsList.add("a");
    String output = myCp.execute(); // copying b contents into a
    assertEquals("", output); // checking no output from cp
    myLs = new Ls(lsList);
    assertEquals("a: \nb\n", myLs.execute()); // checking contents of a still there
  }

  @Test
  public void testCpDirectoryWithFile() {
    mkdirList.add("a");
    mkdirList.add("b");
    mkdirList.add("b/hey");
    mkdirList.add("b/wow");
    myMkDir = new MkDir(mkdirList);
    myMkDir.execute(); // creating directories
    echoList.add("\"hello\"");
    echoList.add(">");
    echoList.add("b/file");
    myEcho = new Echo(echoList);
    myEcho.execute();
    lists.add("b");
    lists.add("a");
    myCp = new Cp(lists);
    String output = myCp.execute(); // copying b contents into a
    assertEquals("", output); // checking no output from cp
    lsList.add("a");
    myLs = new Ls(lsList);
    String lsOutput = myLs.execute();
    assertEquals("a: \nhey\nwow\nfile\n", lsOutput); // checking contents of a
  }
}
