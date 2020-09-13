package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import commands.Cat;
import tree.DirectoryNode;
import tree.Node;

public class DirectoryNodeTest {

  ArrayList<String> lists;
  DirectoryNode myDirNode;

  @Before
  public void setUp() throws Exception {
    myDirNode = new DirectoryNode("/", "test");
  }

  @Test
  public void testAddToList() {
    myDirNode.addToList(new DirectoryNode("/a", "a"));
    assertEquals(1, myDirNode.obtainChildStringList().size());
  }

  @Test
  public void testObtainChildList() {
    myDirNode.addToList(new DirectoryNode("/a", "a"));
    myDirNode.addToList(new DirectoryNode("/b", "b"));
    myDirNode.addToList(new DirectoryNode("/c", "c"));
    myDirNode.addToList(new DirectoryNode("/d", "d"));
    ArrayList<String> list = new ArrayList<String>();
    list.add("/a");
    list.add("/b");
    list.add("/c");
    list.add("/d");
    assertEquals(list, myDirNode.obtainChildStringList());
  }

  @Test
  public void testRemoveNodeFromList() {
    myDirNode.addToList(new DirectoryNode("/a", "a"));
    myDirNode.addToList(new DirectoryNode("/b", "b"));
    myDirNode.removeNodeFromList("/b");
    ArrayList<String> list = new ArrayList<String>();
    list.add("/a");
    assertEquals(list, myDirNode.obtainChildStringList());
  }

}
