package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import driver.Parser;

/**
 * @author Anis
 *
 */
public class ParserTest {

  @Test
  public void testParseInputEmpty() {
    Parser test = new Parser();
    ArrayList<String> expected = new ArrayList<String>();
    ArrayList<String> actual = test.parseInput("");

    assertEquals(expected, actual);

  }

  @Test
  public void testParseInputOneNonString() {
    Parser test = new Parser();
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("arg1");
    ArrayList<String> actual = test.parseInput("arg1");

    assertEquals(expected, actual);
  }

  @Test
  public void testParseInputMultiNonString() {
    Parser test = new Parser();
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("arg1");
    expected.add("arg2");
    expected.add("arg3");
    ArrayList<String> actual = test.parseInput("arg1 arg2 arg3");

    assertEquals(expected, actual);
  }

  @Test
  public void testParseInputMultiWithString() {
    Parser test = new Parser();
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("arg1");
    expected.add("arg2");
    expected.add("\"arg3 is a string\"");
    ArrayList<String> actual = test.parseInput("arg1 arg2 \"arg3 is a string\"");

    assertEquals(expected, actual);
  }

}

