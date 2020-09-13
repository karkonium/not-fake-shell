package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.*;
import tree.Tree;

public class PwdTest {
  ArrayList<String> lists;
  Pwd myPwd;
  Tree dir;
  ArrayList<String> args;
  ArrayList<String> dirs;

  @Before
  public void setUp() throws Exception {

    lists = new ArrayList<String>();
    dir = Tree.getTree();
    args = new ArrayList<String>();
    dirs = new ArrayList<String>();
    Commands.setDir(dir);
  }

  @After
  public void tearDown() throws Exception {
    Field field = (dir.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }

  @Test
  public void testDir() {
    dirs.add("a");
    dirs.add("a/b");
    Commands mkdir = new MkDir(dirs);
    mkdir.execute();

    lists.add("a/b");
    Commands cd = new Cd(lists);
    cd.execute();

    myPwd = new Pwd(args);
    String pwd = myPwd.execute();

    assertEquals("/a/b", pwd);
  }

  @Test
  public void testRoot() {
    myPwd = new Pwd(args);
    String pwd = myPwd.execute();

    assertEquals(pwd, "/");
  }

  @Test
  public void testDirInvalidArgs() {
    dirs.add("a");
    dirs.add("a/b");
    Commands mkdir = new MkDir(dirs);
    mkdir.execute();

    lists.add("a/b");
    Commands cd = new Cd(lists);
    cd.execute();

    args.add("INVALID");
    myPwd = new Pwd(args);
    String pwd = myPwd.execute();

    assertEquals("Invalid Input", pwd);
  }



}
