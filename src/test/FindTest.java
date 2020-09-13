package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Commands;
import commands.Echo;
import commands.Find;
import commands.MkDir;
import tree.Tree;

public class FindTest {
  ArrayList<String> lists;
  Find myFind;
  Tree directory;
  ArrayList<String> tempList;
  MkDir myMkDir;
  Echo myEcho;
  ArrayList<String> tempForEcho;


  @Before
  public void setUp() throws Exception {
    directory = Tree.getTree();
    Commands.setDir(directory);
    lists = new ArrayList<String>();
    tempList = new ArrayList<String>();
    tempForEcho = new ArrayList<String>();
  }

  @After
  public void tearDown() throws Exception {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }

  @Test
  public void testDir() {
    tempList.add("a");
    tempList.add("b");
    tempList.add("a/hey");
    tempList.add("a/wow");
    myMkDir = new MkDir(tempList);
    myMkDir.execute();
    lists.add("a");
    lists.add("-type");
    lists.add("d");
    lists.add("-name");
    lists.add("\"hey\"");
    myFind = new Find(lists);
    String actual = myFind.execute();
    assertEquals("/a/hey  ", actual);
  }

  @Test
  public void testFile() {
    tempList.add("a");
    tempList.add("b");
    tempList.add("a/hey");
    tempList.add("a/wow");
    myMkDir = new MkDir(tempList);
    myMkDir.execute();
    tempForEcho.add("\"d\"");
    tempForEcho.add(">");
    tempForEcho.add("a/file");
    myEcho = new Echo(tempForEcho);
    myEcho.execute();
    lists.add("a");
    lists.add("-type");
    lists.add("f");
    lists.add("-name");
    lists.add("\"file\"");
    myFind = new Find(lists);
    String actual = myFind.execute();
    assertEquals("/a/file  ", actual);
  }

  @Test
  public void testDirDNE() {
    tempList.add("a");
    tempList.add("b");
    tempList.add("a/hey");
    tempList.add("a/wow");
    myMkDir = new MkDir(tempList);
    myMkDir.execute();
    lists.add("a");
    lists.add("-type");
    lists.add("d");
    lists.add("-name");
    lists.add("\"DNE\"");
    myFind = new Find(lists);
    String actual = myFind.execute();
    assertEquals("", actual);
  }
}
