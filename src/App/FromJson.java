package App;

import MarinesThings.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Vector;

/**
 *  The type From json
 */
public class FromJson {
    /**
     *
     * @param data the data
     */
    public static void fillCollection(String data){
        Vector<SpaceMarine> collection = new Vector<>();
        Gson gson = new Gson();
        if (data==null) System.out.println("Указанный файл не найден.Коллекция пустая.");
        else {
            try {
                Type type = new TypeToken<Vector>(){}.getType();
                Vector <LinkedTreeMap> marines = new Gson().fromJson(data,type);
                for (LinkedTreeMap params : marines) {
                    SpaceMarine spaceMarine = new SpaceMarine();
                    spaceMarine.setId(((Double) params.get("id")).intValue());
                    spaceMarine.setName((String) params.get("name"));
                    LinkedTreeMap coordParams = (LinkedTreeMap) params.get("coordinates");
                    Coordinates coordinates = new Coordinates();
                    coordinates.setX(((Double) coordParams.get("x")).intValue());
                    coordinates.setY((Double) coordParams.get("y"));
                    spaceMarine.setCoordinates(coordinates);

                    LinkedTreeMap<String, LinkedTreeMap> creationDate = (LinkedTreeMap) params.get("creationDate");
                    LinkedTreeMap<String, LinkedTreeMap> partsOfDateTime = (LinkedTreeMap) creationDate.get("dateTime");
                    LinkedTreeMap<String, Double> partsOfOffset = (LinkedTreeMap) creationDate.get("offset");
                    LinkedTreeMap<String, String> partsOfZone = (LinkedTreeMap) creationDate.get("zone");
                    LinkedTreeMap<String, Double> partsOfDate = (LinkedTreeMap) partsOfDateTime.get("date");
                    LinkedTreeMap<String, Double> partsOfTime = (LinkedTreeMap) partsOfDateTime.get("time");
                    int year = partsOfDate.get("year").intValue();
                    int month = partsOfDate.get("month").intValue();
                    int day = partsOfDate.get("day").intValue();
                    int hour = partsOfTime.get("hour").intValue();
                    int minute = partsOfTime.get("minute").intValue();
                    int second = partsOfTime.get("second").intValue();
                    int nano = partsOfTime.get("nano").intValue();
                    int totalSeconds = partsOfOffset.get("totalSeconds").intValue();
                    ZoneId zoneId = ZoneId.of(partsOfZone.get("id"));
                    spaceMarine.setCreationDate(ZonedDateTime.of(year,month,day,hour,minute,second,nano,zoneId));

                    spaceMarine.setHealth((Double) params.get("health"));
                    spaceMarine.setHeight((Double) params.get("height"));
                    spaceMarine.setCategory(AstartesCategory.valueOf((String) params.get("category")));
                    spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf((String) params.get("meleeWeapon")));
                    LinkedTreeMap chapterParams = (LinkedTreeMap) params.get("chapter");
                    Chapter chapter = new Chapter();
                    chapter.setName((String)chapterParams.get("name"));
                    chapter.setMarinesCount(((Double)chapterParams.get("marinesCount")).intValue());
                    chapter.setWorld((String)chapterParams.get("world"));
                    spaceMarine.setChapter(chapter);
                    collection.add(spaceMarine);
                }
                Collection.setCollection(collection);
                System.out.println("Коллекция успешно заполнена.");
            } catch (JsonSyntaxException e) {
                System.out.println("Ошибка заполнения.Коллекция пустая");
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("В файле указаны некорретные данные. Коллекция пустая.");
            }

        }
    }

}
