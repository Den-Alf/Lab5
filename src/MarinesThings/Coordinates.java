package MarinesThings;

/**
 * The type Coordinates
 */
public class Coordinates {
    private Integer x; //Значение поля должно быть больше -23, Поле не может быть null
    private Double y; //Поле не может быть null

    /**
     *
     * @return the x
     */
    public Integer getX() {
        return x;
    }

    /**
     *
     * @param x the x
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     *
     * @return the y
     */
    public Double getY() {
        return y;
    }

    /**
     *
     * @param y the y
     */
    public void setY(Double y) {
        this.y = y;
    }
}
