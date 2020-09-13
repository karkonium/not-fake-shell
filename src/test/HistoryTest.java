package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Commands;
import commands.History;

public class HistoryTest {

  ArrayList<String> lists;
  History myHistory;

  @Before
  public void setUp() throws Exception {
    lists = new ArrayList<String>();
    Commands.setDir(new MockTree());

  }

  @Test
  public void testaddCommand() {
    myHistory = new History(lists);
    History.setHistory(lists);
    History.addCommand("mkdir a");
    History.addCommand("history");
    ArrayList<String> his = new ArrayList<String>();
    his = History.getHistory();
    assertEquals("mkdir a history", his.get(0) + " " + his.get(1));
  }

  @Test
  public void testExecuteNoVar() {
    ArrayList<String> his = new ArrayList<String>();
    his.add("mkdir a");
    his.add("history");
    History.setHistory(his);
    myHistory = new History(lists);
    String output = myHistory.execute();
    assertEquals("1. mkdir a" + System.lineSeparator() + "2. history" + System.lineSeparator(),
        output);
  }

  @Test
  public void testEmptyHistory() {
    ArrayList<String> his = new ArrayList<String>();
    History.setHistory(his);
    myHistory = new History(lists);
    String output = myHistory.execute();
    assertEquals("", output);
  }

  @Test
  public void testExecuteVar() {
    ArrayList<String> his = new ArrayList<String>();
    his.add("mkdir a");
    his.add("history");
    History.setHistory(his);
    lists.add("1");
    myHistory = new History(lists);
    String output = myHistory.execute();
    assertEquals("2. history" + System.lineSeparator(), output);
  }

  @Test
  public void testBigArg() {
    ArrayList<String> his = new ArrayList<String>();
    his.add("mkdir a");
    his.add("history");
    History.setHistory(his);
    lists.add("50");
    myHistory = new History(lists);
    String output = myHistory.execute();
    assertEquals("1. mkdir a" + System.lineSeparator() + "2. history" + System.lineSeparator(),
        output);
  }

  @After
  public void tearDown() {
    History.setHistory(null);
  }


}
