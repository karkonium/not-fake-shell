package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.History;
import commands.LoadState;
import commands.Ls;
import commands.MkDir;
import commands.SaveState;
import tree.Tree;
import commands.Commands;

public class SaveStateTest {

  private static final Path Path = null;
  String FileReader = null;
  ArrayList<String> pathToFile;
  ArrayList<String> lists;
  SaveState state;
  History history;
  Tree directory;

  @Before
  public void setUp() {
    state = null;
    lists = new ArrayList<String>();
    pathToFile = new ArrayList<String>();
    directory = Tree.getTree();
    Commands.setDir(directory);
    history = null;
  }

  @Test
  public void testOneFile() {
    history = new History(lists);
    directory.addDirectoryNode("/a");
    pathToFile.add("/Users/clementtran/Desktop/testing123.txt");
    history.addCommand("mkdir a");
    history.addCommand("save /Users/clementtran/Desktop/testing123.txt");
    try {
      state = new SaveState(pathToFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    File file1 = new File("testing123.txt");
    byte[] f1 = null;
    try {
      f1 = Files.readAllBytes(Paths.get("/Users/clementtran/Desktop/testing123.txt"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    int actual = new BigInteger(f1).intValue();
    int expected = 1954051192;

    assertEquals(expected, actual);
  }

  @Test
  public void testFolderDoesNotExist() {
    history = new History(lists);
    directory.addDirectoryNode("/a");
    pathToFile.add("/Users/clementtran/Desktop/rundamgod/testing123.txt");
    history.addCommand("mkdir a");
    history.addCommand("save /Users/clementtran/Desktop/rundamgod/testing123.txt");
    try {
      state = new SaveState(pathToFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String pathToFile = "/Users/clementtran/Desktop/rundamgod/testing123.txt";
    String path[] = pathToFile.split("/");
    int length = path.length;
    String folderPath = "/";
    for (int i = 0; i < length - 1; i++) {
      folderPath = folderPath + "/" + path[i];
    }
    File folders = new File(folderPath);
    boolean actual = folders.exists();
    boolean expected = false;

    assertEquals(expected, actual);
  }

  @After
  public void tearDown() throws Exception {
    Field field = (directory.getClass()).getDeclaredField("dir");
    field.setAccessible(true);
    field.set(null, null); // setting the ref parameter to null
  }
}
