package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Commands;
import commands.Echo;
import commands.Ls;
import tree.Tree;

public class EchoTest {
  ArrayList<String> lists;
  Echo myEcho;
  Tree directory;
  Ls myLs;
  ArrayList<String> lsList;


  @Before
  public void setUp() throws Exception {
    directory = Tree.getTree();
    Commands.setDir(directory);
    lists = new ArrayList<String>();
    lsList = new ArrayList<String>();
  }

  @After
  public void tearDown() throws Exception {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }

  @Test
  public void testRedirection() {
    lists.add("\"d\"");
    lists.add(">");
    lists.add("file");
    myEcho = new Echo(lists);
    String output = myEcho.execute();
    assertEquals("", output);
    myLs = new Ls(lsList);
    String lsActual = myLs.execute();
    assertEquals("file\n", lsActual);
  }

  @Test
  public void testNoRedirection() {
    lists.add("\"d\"");
    myEcho = new Echo(lists);
    String output = myEcho.execute();
    assertEquals("d", output);
    myLs = new Ls(lsList);
    String lsActual = myLs.execute();
    assertEquals("", lsActual);
  }

  @Test
  public void testMultupleFilesCreation() {
    lists.add("\"d\"");
    lists.add(">");
    lists.add("file");
    myEcho = new Echo(lists);
    myEcho.execute(); // creating first file
    ArrayList<String> tempList = new ArrayList<String>();
    tempList.add("\"hello\"");
    tempList.add(">");
    tempList.add("file1");
    myEcho = new Echo(tempList); // creating second file
    String output = myEcho.execute();
    assertEquals("", output); // checking no output
    myLs = new Ls(lsList);
    String lsActual = myLs.execute();
    assertEquals("file\nfile1\n", lsActual); // checking file creation
    assertEquals("d", directory.getFileContents("/file")); // checking file contents
    assertEquals("hello", directory.getFileContents("/file1")); // checking file1 contents
  }


}
