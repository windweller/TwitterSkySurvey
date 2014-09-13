package twitter.util;

import java.io.FileWriter;
import java.io.IOException;
public class Util {

    public static void writeStringToFile(String filePathAndName, String stringToBeWritten) throws IOException{
        try
        {
            FileWriter fw = new FileWriter(filePathAndName, true);
            fw.write(stringToBeWritten);//appends the string to the file
            fw.write("\n");
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
}