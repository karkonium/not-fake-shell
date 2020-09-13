package display;

/**
 * This class prints given outputs onto the screen
 *
 */
public class Display {

  /**
   * Default constructor
   */
  public Display() {}

  public static boolean print = true;

  public static void printStop() {
    print = false;
  }

  /**
   * This method prints out a string in a line
   * 
   * @param inputToPrint A string to be printed
   */
  public void standardOutputLine(String inputToPrint) {
    if (print)
      System.out.println(inputToPrint);
  }


  /**
   * This method prints out a string
   * 
   * @param inputToPrint A string to be printed
   */
  public void standardOutput(String inputToPrint) {
    if (print)
      System.out.print(inputToPrint);
  }
}
