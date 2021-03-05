package MarinesThings;

import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * The type Barracks
 */
public class Barracks {
    private final Scanner sc = new Scanner(System.in);

    /**
     *
     * @param argid the id
     * @return the spaceMarine
     */
    public SpaceMarine create(String argid){
        SpaceMarine spaceMarine = new SpaceMarine();
        if(argid.equals("free")) {
            spaceMarine.setId((int) Collection.getFreeId());
        }
        else{
            spaceMarine.setId(Integer.parseInt(argid));
        }
        this.setName(spaceMarine);
        Coordinates coordinates = new Coordinates();
        this.setCoordX(coordinates);
        this.setCoordY(coordinates);
        spaceMarine.setCoordinates(coordinates);
        this.setHealth(spaceMarine);
        this.setHeight(spaceMarine);
        this.setAstartesCategory(spaceMarine);
        this.setMeleeWeapon(spaceMarine);
        Chapter chapter = new Chapter();
        this.setChapName(chapter);
        this.setChapMCount(chapter);
        this.setChapWorld(chapter);
        spaceMarine.setChapter(chapter);
        spaceMarine.setCreationDate(ZonedDateTime.now());
        return spaceMarine;
    }

    /**
     *
     * @param spaceMarine the Space marine
     */
    public void setName(SpaceMarine spaceMarine){
            System.out.print("Введите имя десантника - ");
            String name = sc.nextLine();
            if (name.equals("") || name.equals("null")) {
                this.setName(spaceMarine);
            } else {
                spaceMarine.setName(name);
            }
        }

    /**
     *
     * @param coordinates the coordinates
     */
    public void setCoordX(Coordinates coordinates)  {
       try {
            System.out.print("Введите координату Х - ");
            String  coordx = sc.nextLine();
            if (coordx.equals("")||coordx.equals(null)) {
                this.setCoordX(coordinates);
            } else {
                Integer x = Integer.parseInt(coordx);
                if (x < -23) {
                    System.out.println("Координата Х должна быть больше -23");
                    this.setCoordX(coordinates);
                } else {
                    coordinates.setX(x);
                }
            }
       } catch (NumberFormatException e) {
            System.out.println("Значение координаты X должно быть типа \"Integer\"");
            this.setCoordX(coordinates);
        }

    }

    /**
     *
     * @param coordinates the coordinates
     */
    public void setCoordY(Coordinates coordinates){
        System.out.print("Введите координату Y - ");
        try {
            String coordy = sc.nextLine();
            if (coordy.equals("")||coordy.equals(null)) {
                this.setCoordY(coordinates);
            } else {
                Double y = Double.parseDouble(coordy);
                coordinates.setY(y);
            }
        } catch (NumberFormatException e){
            System.out.println("Значение координаты Y должно быть типа \"Double\"");
            this.setCoordY(coordinates);
        }
    }

    /**
     *
     * @param spaceMarine the Space marine
     */
    public void setHealth(SpaceMarine spaceMarine){
        System.out.print("Введите значение здоровья десантника или нажмите Enter, чтобы оставить поле пустым - ");
        try {
            String health = sc.nextLine();
            if (!(health.equals("")||health.equals(null))){
                Double hp = Double.parseDouble(health);
                if(hp>0) {
                    spaceMarine.setHealth(hp);
                }
                else {
                    System.out.println("Значение должно быть больше 0");
                    this.setHealth(spaceMarine);
                }
            }
        } catch (NumberFormatException e){
            System.out.println("Значение здоровья должно быть типа \"Double\"");
            this.setHealth(spaceMarine);
        }
    }

    /**
     *
     * @param spaceMarine the Space marine
     */
    public void setHeight(SpaceMarine spaceMarine){
        System.out.print("Введите значение роста десантника или нажмите Enter, чтобы оставить поле пустым - ");
        try {
            String height = sc.nextLine();
            if (!(height.equals("")||height.equals(null))){
                double hgh = Double.parseDouble(height);
                if(hgh>0) {
                    spaceMarine.setHeight(hgh);
                }
                else {
                    System.out.println("Значение должно быть больше 0");
                    this.setHeight(spaceMarine);
                }
            }
        } catch (NumberFormatException e){
            System.out.println("Значение здоровья должно быть типа \"double\"");
            this.setHeight(spaceMarine);
        }
    }

    /**
     *
     * @param spaceMarine the Space Marine
     */
    public void setAstartesCategory(SpaceMarine spaceMarine){
        System.out.println("Введите род войск из списка \nINCEPTOR\nTACTICAL\nLIBRARIAN\nCHAPLAIN\nHELIX");
            String num = sc.nextLine().toUpperCase();
            try {
                spaceMarine.setCategory(AstartesCategory.valueOf(num));
            } catch (Exception e) {
                System.out.println("Значение должно соответствовать перечислинным типам. Введите значение:");
                this.setAstartesCategory(spaceMarine);
            }
    }

    /**
     *
     * @param spaceMarine the Space Marine
     */
    public void setMeleeWeapon(SpaceMarine spaceMarine){
        System.out.println("Введите оружие из списка \nPOWER_SWORD\nCHAIN_AXE\nLIGHTING_CLAW\nPOWER_BLADE");
        String num = sc.nextLine().toUpperCase();
        try {
            spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(num));
        } catch (Exception e) {
            System.out.println("Значение должно соответствовать перечислинным типам. Введите значение:");
            this.setMeleeWeapon(spaceMarine);
        }
    }

    /**
     *
     * @param chapName the Chapter name
     */
    public void setChapName(Chapter chapName){
        System.out.print("Введите название отряда - ");
        String name = sc.nextLine();
        if (name.equals("") || name.equals("null")) {
            this.setChapName(chapName);
        } else {
            chapName.setName(name);
        }

    }

    /**
     *
     * @param chapMCount the Chapter marines count
     */
    public void setChapMCount(Chapter chapMCount) {
        System.out.print("Введите кол-во солдат в отряде - ");
        try {
            String mcount = sc.nextLine();
            if (mcount.equals("") || mcount.equals(null)) {
                this.setChapMCount(chapMCount);
            } else {
                int c = Integer.parseInt(mcount);
                if ((c<1)||(c>1000)) {
                    System.out.println("Количество солдат в отряде должно быть больше 0 и меньше 1001");
                    this.setChapMCount(chapMCount);
                } else {
                    chapMCount.setMarinesCount(c);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Количество солдат в отряде должно быть типа \"int\"");
            this.setChapMCount(chapMCount);
        }
    }

    /**
     *
     * @param chapWorld the Chapter world
     */
    public void setChapWorld(Chapter chapWorld){
        System.out.print("Введите название мира, в котором находится отряд - ");
        String world = sc.nextLine();
        if (world.equals("") || world.equals("null")) {
            this.setChapWorld(chapWorld);
        } else {
            chapWorld.setWorld(world);
        }
    }
}

