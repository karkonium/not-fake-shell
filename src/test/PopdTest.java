package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Stack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.*;
import commands.History;
import commands.Popd;
import tree.Tree;

public class PopdTest {
  Stack<String> stack;
  Popd myPopd;
  Tree directory;
  ArrayList<String> args;
  ArrayList<String> dirs;

  @Before
  public void setUp() throws Exception {
    directory = Tree.getTree();
    Commands.setDir(directory);
    dirs = new ArrayList<String>();
    args = new ArrayList<String>();
    stack = new Stack<String>();

  }

  @After
  public void tearDown() throws Exception {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }

  @Test
  public void testNonEmpty() {
    stack.add("/a");

    dirs.add("a");
    dirs.add("b");
    dirs.add("a/c");

    Commands mkdir = new MkDir(dirs);
    mkdir.execute();

    Commands myPopd = new Popd(args);
    Popd.setStack(stack);
    String output = myPopd.execute();
    assertEquals("", output);
    assertEquals(0, Popd.dirStack.size());
    assertEquals(directory.getCWD(), "/a");


  }

  @Test
  public void testEmpty() {
    dirs.add("a");
    dirs.add("b");
    dirs.add("a/c");

    Commands mkdir = new MkDir(dirs);
    mkdir.execute();

    Commands myPopd = new Popd(args);
    Popd.setStack(stack);
    String output = myPopd.execute();
    assertEquals("", output);
    assertEquals(0, Popd.dirStack.size());
    assertEquals(directory.getCWD(), "/");

  }



}
