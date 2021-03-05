package App;

import MarinesThings.SpaceMarine;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.util.Vector;

/**
 * The type File Writer
 */
public class FileWriter {
    static String filename;


    /**
     *
     * @param filename the file name
     */
    public static void setFilename(String filename) {
        FileWriter.filename = filename;
    }


    /**
     *
     * @param spaceMarines the space marine
     */
    public static void marinesToFile(Vector<SpaceMarine> spaceMarines) {
        Gson gson = new Gson();
        try{
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filename));
            writer.write(gson.toJson(spaceMarines));
            writer.close();
            System.out.println("Запись в файл выполнена успешно");
        } catch (Exception e){
            System.out.println("Файл потерялся");
            e.printStackTrace();
        }
    }

}
