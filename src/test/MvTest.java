package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Stack;
import commands.*;
import tree.Tree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MvTest {
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
    // Display.printStop();

  }

  @After
  public void tearDown() throws Exception {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }



  @Test
  public void testMoveFile() {
    dirs.add("a");
    Commands mkdir = new MkDir(dirs);
    mkdir.execute();

    dirs.clear();
    dirs.add("\"output\"");
    dirs.add(">");
    dirs.add("out");

    Commands echo = new Echo(dirs);
    echo.execute();

    args.add("out");
    args.add("a");

    Commands mv = new Mv(args);
    String out = mv.execute();
    assertEquals("", out);

    dirs.clear();
    dirs.add("a");
    Commands ls = new Ls(dirs);
    String check = ls.execute();

    assertEquals("a: \nout\n", check);
  }

  @Test
  public void testMoveDir() {
    dirs.add("a");
    dirs.add("b");

    Commands mkdir = new MkDir(dirs);
    mkdir.execute();

    args.clear();
    args.add("b");
    args.add("a");

    Commands mv = new Mv(args);
    String out = mv.execute();
    assertEquals("", out);

    dirs.clear();
    dirs.add("a");
    Commands ls = new Ls(dirs);
    String check = ls.execute();

    assertEquals("a: \n", check);
  }

  @Test
  public void testDirMissing() {
    dirs.add("a");

    Commands mkdir = new MkDir(dirs);
    mkdir.execute();

    args.clear();
    args.add("b");
    args.add("a");

    Commands mv = new Mv(args);
    String out = mv.execute();
    assertEquals("", out);

    dirs.clear();
    dirs.add("a");
    Commands ls = new Ls(dirs);
    String check = ls.execute();


    assertEquals("a: \n", check);
  }



}
