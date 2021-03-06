/*
File: WebSaver.java
Author: Ethan J. Nephew
Date due: April 4, 2021
Course: CEN-3024C
Description: This is the class definition that downloads the text from the internet and saves it. It conducts some basic filtering as well.
*/

package WordCounter;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/***
 * Method that is used to read a source page.
 */
public class WebSaver implements CONSTANTS {

    /***
     * Method used to download the specified HTML source page and conduct basic text filtering. Filters text based on individual line contents.
     */
    public static void writePageToFile() {
        try {
            URL url = new URL(websiteURL);
            Scanner scan = new Scanner(url.openStream());
            String content = "";
            content = readText(scan);
            WriteToFile.writeThisToFile(downloadDirectory, content);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    /***
     * This method scans the text from the specified webpage. It will scan all the text until there are no more lines to scan.
     * @param scan The parameter is a scanner.
     * @return It will return the content that was scanned.
     */
    public static String readText(Scanner scan){
        String reader = "";
        String content = "";
        while (scan.hasNextLine()) {
            reader = scan.nextLine() + "\n";
            if (checkLineContent(reader))
                content = content.concat(reader);
        }
        return content;
    }

    /***
     * This method reads a string and returns true if it contains strings of interest.
     * @param reader This is the string that will be evaluated.
     * @return It will return true if the string contains the strings of interest.
     */
    public static boolean checkLineContent(String reader){
        // This method contains critical functionality.
        if (reader.contains("<br />") && !reader.contains("<div") && !reader.contains("<span") || reader.contains("<span style=\"margin-left: 20%\">")) // specifies textual markers
            return true;
        else
            return false;
    }
}
