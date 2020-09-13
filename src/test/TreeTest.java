package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.Commands;
import display.Display;
import tree.Tree;

public class TreeTest {
  Tree directory;

  @Before
  public void setUp() throws Exception {
    directory = Tree.getTree();
    Display.printStop();
  }

  @After
  public void tearDown() throws Exception {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }

  @Test
  public void testSingleTon() {
    Tree newDirectory = Tree.getTree();
    assertEquals(newDirectory, directory);
  }

  @Test
  public void testUpdateCWD() {
    directory.addDirectoryNode("/yollo");
    directory.updateCWD("/yollo");
    assertEquals("/yollo", directory.getCWD());
  }

  @Test
  public void testRecursiveTree() {
    directory.addDirectoryNode("/y");
    directory.addDirectoryNode("/z");
    directory.addDirectoryNode("/x");
    directory.addDirectoryNode("/y/x");
    directory.addDirectoryNode("/y/z");
    assertEquals("	y\n" + "		x\n" + "		z\n" + "	z\n" + "	x\n",
        directory.recursiveTree("/", 0, ""));
  }

  @Test
  public void testValidateFileExistence() {
    directory.addDirectoryNode("/x");
    assertTrue(directory.validateFileExistence("x"));
  }

  @Test
  public void testAddDirectoryNode() {
    directory.addDirectoryNode("/abc");
    directory.addDirectoryNode("/abc/def");
    directory.addDirectoryNode("/abc/def/ghi");
    assertTrue(directory.validateFileExistence("/abc/def/ghi"));
  }

  @Test
  public void testAddFileNode() {
    directory.addFileNode("/abc", "Contents");
    assertTrue(directory.validateFileExistence("/abc"));
    assertEquals("Contents", directory.getFileContents("/abc"));
  }

  @Test
  public void testGetNameOfParent() {
    directory.addDirectoryNode("/abc");
    directory.addFileNode("/abc/k", "child");
    directory.addDirectoryNode("/abc/def");
    assertEquals("abc", directory.getNameOfParent("/abc/def"));
  }

  @Test
  public void testGetFileContents() {
    directory.addDirectoryNode("/abc");
    directory.addFileNode("/abc/k", "child");
    assertEquals("child", directory.getFileContents("/abc/k"));
  }

  @Test
  public void testRemoveNode() {
    directory.addDirectoryNode("/abc");
    directory.addDirectoryNode("/abc/def");
    directory.removeNode("/abc/def");
    assertTrue(!directory.validateFileExistence("/abc/def"));
  }

  @Test
  public void testGetFilesInDirectory() {
    directory.addDirectoryNode("/abc");
    directory.addDirectoryNode("/abc/def");
    directory.addDirectoryNode("/abc/k");
    directory.addDirectoryNode("/abc/m");
    ArrayList<String> list = new ArrayList<String>();
    list.add("def");
    list.add("k");
    list.add("m");
    assertEquals(list, directory.getFilesInDirectory("/abc"));
  }

  @Test
  public void testIsDirectroy() {
    directory.addDirectoryNode("/abc");
    assertTrue(directory.validateFileExistence("/abc"));
    assertTrue(directory.isDirectory("/abc"));
  }

  @Test
  public void testGetSubDirectories() {
    directory.addDirectoryNode("/abc");
    directory.addDirectoryNode("/abc/def");
    directory.addDirectoryNode("/abc/k");
    directory.addDirectoryNode("/abc/m");
    directory.addDirectoryNode("/abc/m/mm");
    ArrayList<String> list = new ArrayList<String>();
    list.add("/abc/def");
    list.add("/abc/k");
    list.add("/abc/m");
    list.add("/abc/m/mm");
    assertEquals(list, directory.getSubDirectories("/abc"));
  }

}
