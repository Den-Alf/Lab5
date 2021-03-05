package App;

import MarinesThings.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 *The type Switcher
 */
public class Switcher {
    Collection collection = new Collection();
    int exit = 0;
    String output="";
    int scriptCommand = 0;


    /**
     *Starts the app
     */
    public void start() {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Добро пожаловать в \"Таск Манагер by Den_Alf\"");
        String additInfo = "";

        try {
            while (exit == 0) {
                System.out.print("Введите команду: ");
                String input = inp.readLine();
                int spaceIndex = input.indexOf(" ");
                if (spaceIndex != -1) {
                    additInfo = input.substring(spaceIndex + 1);
                    input = input.substring(0, spaceIndex);
                }
                String[] substr = input.split(" ");
                String command = substr[0];
               output = commandchoicer(command,additInfo);
               System.out.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Упс, что-то пошло не так");
        }
    }

    /**
     *Define the command
     *
     * @param command the command
     * @param additInfo the additional info
     * @return the output
     */
    public String commandchoicer (String command, String additInfo) {
                    switch (command) {
                        case "help":
                            help();// Инфа
                            break;

                        case "info":
                            output = info();
                            break;

                        case "show":
                            output = show();
                            break;

                        case "add":
                            output = add();//Добавление элементов в коллекцию
                            break;

                        case "update":
                            output = update(additInfo);
                            break;

                        case "remove_by_id":
                            output = remove_by_id(additInfo);
                            break;

                        case "clear":
                            output = clear();
                            break;

                        case "save":
                                save();  //Сохранение в файл
                            break;

                        case "exit":
                            System.out.println("Завершение программы без сохранения данных");
                            System.exit(0); // Выход
                            break;

                        case "insert_at":
                            output = insert_at(additInfo);
                            break;

                        case "remove_first":
                            output = remove_first();
                            break;

                        case "reorder":
                            output = reorder();
                            break;

                        case "min_by_chapter":
                            output = min_by_chapter();
                            break;

                        case "count_less_than_height":
                            output = count_less_than_height(additInfo);
                            break;

                        case "print_field_ascending_melee_weapon":
                            output = print_field_ascending_melee_weapon();
                            break;

                        case "execute_script":
                            scriptCommand = 1;
                            output = execute_script(additInfo);
                            break;

                        default:
                            output = ("Командна неопознана, попробуйте ещё раз" + System.lineSeparator() + "Для вызова подсказки используйте help");
                    }
                        return output;
                }


    /**
     *
     *
     * @return the info about collection
     */
    public String info(){
        return collection.getInfo();
    }

    /**
     *
     * @return the info about instances
     */
    public String show(){
        if(collection.getEmpty()==1) return  ("Коллекция пуста");
        else{
            String result ="";
            for (SpaceMarine spaceMarine : Collection.getCollection()) {
                result +=(spaceMarine.getInfo())+"\n\n";
            }
            return result;
        }

    }

    /**
     *
     * @return the confirming phrase
     */
    public String add(){
        collection.add(new Barracks().create("free"));
        return "Десантник к бою готов";
    }

    /**
     *
     * @param findId the id to update
     * @return the confirming phrase
     */
    public String update(String findId) {
        try {
            if (collection.isIdBusy(Integer.parseInt(findId))) {
                int id=Integer.parseInt(findId);
                SpaceMarine deletemarine = Collection.getCollection().stream().filter(x->x.getId()==id).findFirst().get();
                collection.add((new Barracks()).create(findId));
                Collection.getCollection().remove(deletemarine);
                return  "Космодесантник [id:" + findId + "] успешно обновлен.";
            } else return "Элемента с таким id не существует.";
        } catch (Exception e) {
            return  "Элемента с таким id не существует.";
        }
    }

    /**
     *
     * @param findId the id to remove
     * @return the confirming phrase
     */
    public String remove_by_id(String findId){
        try {
            if (collection.getSize() == 0) return ("Коллекция пустая.");
            else {
                boolean isRemoved = false;
                int id = 0;
                try {
                    id = Integer.parseInt(findId);
                } catch (NumberFormatException exp) {
                    return  "Неверно указан аргумент.";
                }

                Iterator<SpaceMarine> it = Collection.getCollection().iterator();
                while (it.hasNext()) {
                    SpaceMarine spaceMarine = it.next();
                    int Id = (int) spaceMarine.getId();
                    if (id == Id) {
                        it.remove();
                        isRemoved = true;
                        return "Космодесантник успешно удален.";
                    }
                }
                if (!isRemoved) return "Элемента с таким id не существует.";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return the confirming phrase
     */
    public String clear(){
        if (collection.getSize()==0) return "Коллекция и так пустая.";
        else{
            collection.clear();
            return  "Коллекция успешно очищена.";
        }
    }

    /**
     * Saves the collection
     */
    public void save()  {
        FileWriter.marinesToFile(Collection.getCollection());
    }

    /**
     *
     * @param scriptname the script file
     * @return the result
     */
    public String execute_script(String scriptname){
        ArrayList<String> exScrHistory = new ArrayList<>();
        scriptCommand = 1;
        try {
            exScrHistory.add("execute_script "+scriptname);
            String result = "";
            File file = new File(scriptname);
            Scanner sc = new Scanner(file);
            String exAdditInfo= "";
            while (sc.hasNextLine()) {
                String input=sc.nextLine();
                String[] exArg = input.split(" ");
                String command = exArg[0];
                if(exArg.length == 2) {
                    exAdditInfo = exArg[1];
                }
                if (!(command.equals(""))) {
                    if (!(command.equals("execute_script " + exAdditInfo))) {
                        result+=("Команда \"" + command + " " + exAdditInfo + "\":\n");
                        result+=commandchoicer(command,exAdditInfo);
                        result+="\n";
                    }
                    else {
                        if (exScrHistory.contains("execute_script " + exAdditInfo)) {
                            result+=("Команда \"" + command + exAdditInfo +  "\": невыполнима во избежание бесконечной рекурсии .\n\n");
                        }
                        else {
                            exScrHistory.add("execute_script " + exAdditInfo);
                            result+=("Выполнение вложенного скрипта \"" + command + exAdditInfo + "\":\n");
                            result+=commandchoicer(command,exAdditInfo);
                            result+="Выполнение вложенного скрипта завершено.\n\n";
                        }
                    }
                }
            }
            exScrHistory.clear();
            scriptCommand=0;
            return result;
        } catch (NullPointerException | FileNotFoundException e) {
            return ("Указанный файл не найден.");
        }
    }

    /**
     *
     * @param index the index to insert at
     * @return the confirming phrase
     */
    public String insert_at(String index){
        try {
            Collection.getCollection().add(Integer.parseInt(index), (new Barracks()).create("free"));
            return ("Космодесантник успешно добавлен.");
        }catch (Exception e){
            return "Аргумент указан неверно.";
        }
    }

    /**
     *
     * @return the confirming phrase
     */
    public String remove_first(){
        try {
            if (collection.getSize() == 0) return ("Коллекция пустая.");
            else {
                 Iterator<SpaceMarine> it = (Iterator<SpaceMarine>) Collection.getCollection().iterator();
                 SpaceMarine spaceMarine = (SpaceMarine) it.next();
                 it.remove();
                 return "Космодесантник успешно удален.";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return the confirming phrase
     */
    public String reorder(){
        try {
            Collection collection = new Collection();
            int size = collection.getSize();
            int j = size-1;
            for (int i = 0; i < size / 2; i++) {
                Collections.swap(Collection.getCollection(), i, j);
                j--;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("");
        }
        return "Коллекция успешно отсортирована в обратном порядке.";
    }

    /**
     *
     * @return the min by chapter
     */
    public String min_by_chapter(){
        if(collection.getEmpty()==1) return  ("Коллекция пуста");
        else{
            String result ="";
            int i = 0;
            int comparer = 1000;
            for (SpaceMarine spaceMarine : Collection.getCollection()) {
                if(i==0){
                   comparer = spaceMarine.getChapter().getMarinesCount();
                }
                if(comparer<spaceMarine.getChapter().getMarinesCount()){
                    comparer=spaceMarine.getChapter().getMarinesCount();
                    result =(spaceMarine.getInfo());
                }
                i++;
            }
            return result;
        }
    }

    /**
     *
     * @param strhi the height
     * @return the count of less than height
     */
    public String count_less_than_height(String strhi) {
        try {
            if (collection.getEmpty() == 1) return ("Коллекция пуста");
            else {
                String result = "";
                int i = 0;
                double comparer = Double.parseDouble(strhi);
                for (SpaceMarine spaceMarine : Collection.getCollection()) {
                    if (spaceMarine.getHeight()<comparer){
                        i+=1;
                    }
                }
                result+=i;
                return result;
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
            return "Неверный формат данных, ожидалась высота типа \"Double\" ";
        }
    }

    /**
     *
     * @return the ascending melee weapon list
     */
    public String print_field_ascending_melee_weapon(){
        if(collection.getEmpty()==1) return  ("Коллекция пуста");
        else{
            String result ="";
            collection.mWSort();
            for (SpaceMarine spaceMarine : Collection.getCollection()) {
                result +=("\n[id:"+spaceMarine.getId()+"]\n"+spaceMarine.getMeleeWeapon())+"\n~~~~~~~~~~~~~~~~~";
            }
            collection.sort();
            return result;
        }
    }


    /**
     * Prints the help
     */
    public static void help() {
        String helpInfo = "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "insert_at index {element} : добавить новый элемент в заданную позицию\n" +
                "remove_first : удалить первый элемент из коллекции\n" +
                "reorder : отсортировать коллекцию в порядке, обратном нынешнему\n" +
                "min_by_chapter : вывести любой объект из коллекции, значение поля chapter которого является минимальным\n" +
                "count_less_than_height height : вывести количество элементов, значение поля height которых меньше заданного\n" +
                "print_field_ascending_melee_weapon : вывести значения поля meleeWeapon всех элементов в порядке возрастания";
        System.out.println(helpInfo);
    }
}
