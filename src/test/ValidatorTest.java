package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import display.Display;
import validation.Validator;
import java.util.ArrayList;


public class ValidatorTest {
  Display display;
  
  @Before
  public void setUp() {
    display = new Display();
    display.printStop();
  }
  @Test
  public void testValidateInt() {
    Validator test = new Validator();
    String arg1 = "234234";
    String arg2 = "3423 23423";

    boolean exp1 = true;
    boolean exp2 = false;

    assertEquals(exp1, test.validateInt(arg1));
    assertEquals(exp2, test.validateInt(arg2));
  }

  @Test
  public void testValidatePathName() {
    Validator test = new Validator();
    String arg1 = "this/is/../a/./valid/path/name";
    String arg2 = "This!/Is!/Invalid!";

    boolean exp1 = true;
    boolean exp2 = false;

    assertEquals(exp1, test.validatePathName(arg1));
    assertEquals(exp2, test.validatePathName(arg2));


  }

  @Test
  public void testValidateString() {
    Validator test = new Validator();
    String arg1 = "\"This is ^*78^*&^&*^& a Valid String.\"";
    String arg2 = "\"This string is invalid";

    boolean exp1 = true;
    boolean exp2 = false;

    assertEquals(exp1, test.validateString(arg1));
    assertEquals(exp2, test.validateString(arg2));


  }

  @Test
  public void testValidateSymbol() {
    Validator test = new Validator();
    String arg1 = ">>";
    String arg2 = ">";
    String arg3 = "!";

    boolean exp1 = true;
    boolean exp2 = true;
    boolean exp3 = false;

    assertEquals(exp1, test.validateSymbol(arg1));
    assertEquals(exp2, test.validateSymbol(arg2));
    assertEquals(exp3, test.validateSymbol(arg3));


  }

}
