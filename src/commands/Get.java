package commands;

import java.util.ArrayList;
import Networking.DataFetcher;
import Networking.GetData;
import java.net.*;
import java.io.*;

/**
 * This method gets the gets the content in a file given a url
 *
 */
public class Get extends Commands {

  private static String methodDocumentation = "NAME: get\nSYNOPSIS: get URL\nDESCRIPTION:"
      + "Retrieve the file at that URL and add it to the current working directory";

  private static DataFetcher rd;

  /**
   * Constructs object for Get which retrieves contents and creates file in cwd
   * 
   * @param args Contains URL that user wants retrieve file from
   */
  public Get(ArrayList<String> args) {
    arguments = args;
    setRd(new GetData());
  }



  /**
   * This method completes the function get command code in execute function is taken and slightly
   * modified from: https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
   * 
   */
  public String execute() {
    if (validateArgs()) {
      try {
        if (!toRedirect()) {
          String urlContents = rd.getDataFromUrl(arguments.get(0));
          output = urlContents;
        } else {
          display.standardOutputLine("Cannot redirect");
        }
      } catch (Exception e) {
        display.standardOutputLine("Invalid input");
      }
    } else {
      display.standardOutputLine("Invalid input");
    }
    return "";
  }

  /**
   * This method sets the value of the data fetched
   * 
   * @param dataFetch The data from url
   */
  public static void setRd(DataFetcher dataFetch) {
    rd = dataFetch;
  }


  /**
   * Determines if arguments provided by user fit with the command signature
   * 
   * @return true if arguments are valid
   */
  protected boolean validateArgs() {
    if (!super.validateArgs())
      return false;
    if (!(arguments.size() == 1 || arguments.size() == 2))
      return false;
    if (arguments.size() == 2 && !toRedirect())
      return false;
    return true;
  }

  /**
   * Static method that returns the documentation for the specific command in a string format
   * 
   * @return Method documentation in string form
   */
  public static String getDocumentation() {
    return methodDocumentation;
  }

}
