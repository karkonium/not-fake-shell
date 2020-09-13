package Networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import commands.Commands;
import fileIO.FileIO;

/**
 * This class is responsible for obtaining data from a url
 *
 */
public class GetData extends Commands implements DataFetcher {
  FileIO fileio = new FileIO();

  /**
   * This method obtains the data from a given url
   * 
   * @param url The url
   * @return The contents of the url
   */
  public String getDataFromUrl(String url) throws Exception {
    try {
      URL urlLink = new URL(url);
      URLConnection con = urlLink.openConnection();
      BufferedReader stringToRead = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      String contents = "";
      while ((inputLine = stringToRead.readLine()) != null)
        contents = contents + "\n" + inputLine;

      stringToRead.close();
      if (!directory.validateFileExistence(
          directory.getCWD() + "/" + url.substring(url.lastIndexOf("/") + 1))) {
        directory.addFileNode(directory.getCWD() + "/" + url.substring(url.lastIndexOf("/") + 1),
            contents);
      } else {
        fileio.writeOntoFile(directory.getCWD() + "/" + url.substring(url.lastIndexOf("/") + 1),
            contents);
      }

      return contents;
    } catch (Exception e) {
      return "Invalid URL";
    }
  }

  @Override
  /**
   * This method returns null and does not complete anything
   * 
   * @return returns null
   */
  public String execute() {
    return null;
  }



}
