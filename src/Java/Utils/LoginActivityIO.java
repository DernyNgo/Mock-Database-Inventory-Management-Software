package Java.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Daniel Ngo
 * The LoginActivityIO class provides methods for reading and writing data regarding Login Attempts.
 */

public class LoginActivityIO {
    /**
     * Reads data from a file.
     * @param fileName The Name of the File
     * @return Returns data read from the file
     */
    public static String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
    /**
     * Writes data to a file.
     * @param data The data to be written
     * @param fileName The Name of the File
     */
    public static void writeFile(String fileName, String data) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(data + System.lineSeparator());
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
