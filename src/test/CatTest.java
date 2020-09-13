package test;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import commands.Cat;
import commands.Commands;

public class CatTest {
  ArrayList<String> lists;
  List<String> newList;
  Cat myCat;

  @Before
  public void setUp() throws Exception {
    Commands.setDir(new MockTree());
    lists = new ArrayList<String>();
    newList = new ArrayList<String>();
  }

  @Test
  public void testOneFile() {
    lists.add("/valid");
    myCat = new Cat(lists);
    String actualOutput = myCat.execute();
    assertEquals("Obtains data from file\n", actualOutput);

  }

  @Test
  public void testMultipleFiles() {
    lists.add("/valid");
    lists.add("/valid");
    myCat = new Cat(lists);
    String actualOutput = myCat.execute();
    assertEquals("Obtains data from file\n\n\n\nObtains data from file", actualOutput);
  }

  @Test
  public void testRedirection() {
    lists.add("/valid");
    lists.add(">");
    lists.add("file1");
    myCat = new Cat(lists);
    String actualOutput = myCat.execute();
    assertEquals("", actualOutput);
  }

  @Test
  public void testInvalidFile() {
    lists.add("/invalid");
    myCat = new Cat(lists);
    String actualOutput = myCat.execute();
    assertEquals("", actualOutput);

  }


}
