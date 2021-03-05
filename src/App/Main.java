package App;

import MarinesThings.SpaceMarine;

import java.util.Vector;

/**
 * The type Main
 */
public class Main {
    /**
     *
     * @param args the input args
     */
    public static void main(String[] args) {
        FromJson.fillCollection(FileReader.read(args[0]));
        Switcher app = new Switcher();
        app.start();
    }
}
