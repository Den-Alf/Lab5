package MarinesThings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The type Collection
 */
public class Collection {
    private static Vector<SpaceMarine> collection = new Vector<SpaceMarine>();
    private static LocalDate creationDate;

    /**
     *Instantiates a new collection
     */
    public Collection(){ creationDate = LocalDate.now();}

    /**
     *
     * @param id the id
     * @return the boolean
     */
    public boolean isIdBusy(int id){
        for(SpaceMarine spaceMarine : collection)
            if(spaceMarine.getId() == id) return true;
         return false;
    }

    /**
     *
     * @return the id
     */
    public static long getFreeId(){
        long size = collection.size();
        Hashtable<Integer, String > busyIds= new Hashtable<Integer, String>();
        for (SpaceMarine spaceMarine : collection){
            busyIds.put(spaceMarine.getId(), "id");
        }
        int id = 1;
        while (true){
            if (busyIds.get(id) == null) {
                return id;
            }
            else id ++;
        }
    }

    /**
     *
     * @return the collection info
     */
    public String getInfo(){
        return "Тип коллекции: Vector"+"\nДата инициализации: "+creationDate+"\nКоличество элементов: "+collection.size();
    }

    /**
     *
     * @return the empty/not empty
     */
    public Integer getEmpty(){
        int empty;
        if (collection.isEmpty()) {
            empty = 1;
        }
        else{
            empty = 0;
        }
        return empty;
    }

    /**
     *
     * @return the size
     */
    public int getSize(){return collection.size();}

    /**
     * Clear the collection
     */
    public void clear(){collection.clear();}

    /**
     *
     * @return the collection
     */
    public static Vector<SpaceMarine> getCollection(){ return collection; }

    /**
     *
     * @param collection the collection
     */
    public static void setCollection(Vector<SpaceMarine> collection) {
        Collection.collection = collection;
    }

    /**
     *
     * @param spaceMarine the Space marine
     */
    public void add(SpaceMarine spaceMarine) {collection.add(spaceMarine);}

    /**
     * Sorts the collection by melee weapon
     */
    public void mWSort() {
        Collections.sort(collection, Comparator.comparing(SpaceMarine::getMeleeWeapon,Collections.reverseOrder()));}

    /**
     * Sorts the collection
     */
    public void sort(){
        collection.sort(SpaceMarine::compareTo);
    }
}
