package test;

import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import display.Display;
import driver.Driver;

public class DriverTest {
  Driver driver;
  ArrayList<String> list;
  Display display;

  @Before
  public void setUp() throws Exception {
   display = new Display();
   display.printStop();
   driver = new Driver(); 
   list = new ArrayList<String>();
  }

  @Test
  public void testChooseCommandPass() throws InstantiationException, NoSuchMethodException,
      SecurityException, IllegalArgumentException, InvocationTargetException {
    String actual = driver.chooseCommand("mkdir", list);
    assertEquals("MkDir successful", actual);
  }

  @Test
  public void testChooseCommandFail() throws InstantiationException, NoSuchMethodException,
      SecurityException, IllegalArgumentException, InvocationTargetException {
    String actual = driver.chooseCommand("dne", list);
    assertEquals("Command does not exist", actual);
  }

}
