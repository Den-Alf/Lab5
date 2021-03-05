package MarinesThings;

/**
 * The type Chapter
 */
public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private String world; //Поле может быть null

    /**
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return name
     */
    public int getMarinesCount() {
        return marinesCount;
    }

    /**
     *
     * @param marinesCount the marines count
     */
    public void setMarinesCount(int marinesCount) {
        this.marinesCount = marinesCount;
    }

    /**
     *
     * @return the world
     */
    public String getWorld() {
        return world;
    }

    /**
     *
     * @param world the world
     */
    public void setWorld(String world) {
        this.world = world;
    }
}
