// **********************************************************
// Assignment2:
// Student1: Shree Shah
// UTORID user_name:shahsh16
// UT Student #: 1005309104
// Author:
//
// Student2: Clement Tran
// UTORID user_name:tranclem
// UT Student #:1005412144
// Author:
//
// Student3: Anand Karki
// UTORID user_name:karkiana
// UT Student #:1005383531
// Author:
//
// Student4: Anis Saha
// UTORID user_name: sahaani2
// UT Student #: 1005101284
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package driver;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;
import display.*;
import tree.*;
import commands.*;
import fileIO.*;

/**
 * This class contains main which allows the user to enter commands and use the shell
 *
 */
public class JShell {

  private static ParentTree dir = Tree.getTree();
  private static FileIO fileReader = new FileIO();
  private static Parser parser = new Parser();
  private static Driver driver = new Driver();
  private static Display display = new Display();

  public static void main(String[] args) throws InstantiationException, NoSuchMethodException,
      SecurityException, IllegalArgumentException, InvocationTargetException {

    Commands.setDir(dir);
    fileReader.setDirectory(dir);
    Stack<String> dirStack = new Stack<String>();
    Popd.setStack(dirStack);
    Pushd.setStack(dirStack);
    final String ex = "exit";
    Scanner userInput = new Scanner(System.in);

    while (true) {
      display.standardOutput(dir.getCWD() + "# ");
      String com = userInput.next();
      String arguments = (userInput.nextLine()).trim().replaceAll(" +", " ");
      if (com.equals(ex)) {
        if (arguments.length() == 0)
          break;
        display.standardOutputLine("Exception: exit must have 0 arguments");
        continue;
      }

      ArrayList<String> argsParced = new ArrayList<String>();
      argsParced = parser.parseInput(arguments);
      if (argsParced == null)
        continue;

      displayer(com, arguments, argsParced);
    }
    System.exit(0);
  }

  private static void displayer(String com, String arguments, ArrayList<String> argsParced)
      throws InstantiationException, NoSuchMethodException, SecurityException,
      IllegalArgumentException, InvocationTargetException {
    display.standardOutputLine(com);
    display.standardOutputLine(arguments);
    History.addCommand(com + " " + arguments);
    driver.chooseCommand(com, argsParced);
  }
}
