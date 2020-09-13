package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import commands.Get;
import display.Display;

public class GetTest {
  Get myGet;
  ArrayList<String> lists;

  Display display;
  
  @Before
  public void setUp() throws Exception {
    display = new Display();
    display.printStop();
    lists = new ArrayList<String>();
  }

  @Test
  public void testValidPath() {
    lists.add("/validpath");
    myGet = new Get(lists);
    Get.setRd(new MockObjectGetData());
    myGet.execute();
    String actualOutput = myGet.getOutput();
    assertEquals("text on the site", actualOutput);
  }

  @Test
  public void testInvalidPath() {
    lists.add("/thisiswrong");
    myGet = new Get(lists);
    Get.setRd(new MockObjectGetData());
    myGet.execute();
    String actualOutput = myGet.getOutput();
    assertEquals("Invalid path", actualOutput);
  }


}
