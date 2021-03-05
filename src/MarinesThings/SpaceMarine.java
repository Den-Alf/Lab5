package MarinesThings;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Vector;

/**
 * The type Space marine
 */
public class SpaceMarine implements Comparable{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double health; //Поле может быть null, Значение поля должно быть больше 0
    private double height;
    private AstartesCategory category; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле может быть null
    private Chapter chapter; //Поле не может быть null

    /**
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     *
     * @return the creation date
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     *
     * @return the health
     */
    public Double getHealth() {
        return health;
    }

    /**
     *
     * @param health the health
     */
    public void setHealth(Double health) {
        this.health = health;
    }

    /**
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     *
     * @return the category
     */
    public AstartesCategory getCategory() {
        return category;
    }

    /**
     *
     * @param category the category
     */
    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    /**
     *
     * @return the melee weapon
     */
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    /**
     *
     * @param meleeWeapon the melee weapon
     */
    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    /**
     *
     * @return the chapter
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     *
     * @param chapter the chapter
     */
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    /**
     *
     * @return the info
     */
    public String getInfo(){
        return "Космодесантник [id:"+id+"]\n\t" + "Имя: " + name + "\n\tКоординаты:\n\t\tx: " + coordinates.getX() +
        "\n\t\ty: " + coordinates.getY() +  "\n\tДата cоздания: " + creationDate + "\n\tЗдоровье: " + health +
               "\n\tРост: "+ height + "\n\tРод войск: " + category + "\n\tОружие: " + meleeWeapon+ "\n\tОтряд: \n\t\tНазвание: " + chapter.getName() + "\n\t\tКол-во солдат: "+chapter.getMarinesCount()+"\n\t\tМир: "+chapter.getWorld();
    }

    @Override
    public int compareTo(Object o) {
        SpaceMarine spaceMarine = (SpaceMarine) o;
        return this.getName().compareTo(spaceMarine.getName());
    }
}
