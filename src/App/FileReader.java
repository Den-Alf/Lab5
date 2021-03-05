package App;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The type File reader
 */
public class FileReader {
    /**
     *
     * @param filename the file name
     * @return the data
     */
    public static String read(String filename){
        try {
            Path path = Paths.get(filename);
            Scanner sc = new Scanner(path);
            String data = sc.nextLine();
            FileWriter.setFilename(filename);
            sc.close();
            return data;
        } catch (NullPointerException | NoSuchFileException e) {
            e.printStackTrace();
            System.out.println("Вы забыли указать имя файла.");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
