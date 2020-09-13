package driver;

import java.util.ArrayList;
import display.*;

/**
 * This class formats user input based on the output that we need per command
 */
public class Parser {

  private static Display display = new Display();

  /**
   * Parses input string into ArrayList<String> of arguments
   * 
   * 
   * @param input is user input to be parsed
   * @exception e if quotation marks are unclosed
   * 
   * @return parsed arguments in array list
   */
  public ArrayList<String> parseInput(String input) {

    boolean parseBySpace = true;
    ArrayList<String> args = new ArrayList<String>();

    int lastSpace = -1;
    for (int i = 0; i < input.length(); i++) {

      if (input.charAt(i) == '"') {
        parseBySpace = !parseBySpace;
      }

      else if (parseBySpace && input.charAt(i) == ' ') {
        args.add(input.substring(lastSpace + 1, i));
        lastSpace = i;
      }
    }

    if (!parseBySpace) {
      display.standardOutputLine("Exception: unclosed quotation in input");
      return null;
    }

    if (input.length() > 0) // edge case 0 args
      args.add(input.substring(lastSpace + 1));

    return args;
  }
}
