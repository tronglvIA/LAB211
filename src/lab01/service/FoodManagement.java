package lab01.service;

import lab01.model.Food;
import lab01.util.Input;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class FoodManagement {
    private final ArrayList<Food> listFood;

    public FoodManagement() {
        this.listFood = new ArrayList<>();
    }


    public void menu(){
        System.out.println("=========================================================================================");
        System.out.println("||                                      MENU                                           ||");
        System.out.println("=========================================================================================");
        System.out.println("||\t\t\t1. Add a New Food.                                                         ||");
        System.out.println("||\t\t\t2. Search Food By Name.                                                    ||");
        System.out.println("||\t\t\t3. Remove Food by Id.                                                      ||");
        System.out.println("||\t\t\t4. Print the food list in the descending order of expired date.            ||");
        System.out.println("||\t\t\t5. Store the food list as text (*.txt) file.                               ||");
        System.out.println("||\t\t\t6. Quit.                                                                   ||");
        System.out.println("=========================================================================================");
    }

    public void searchByName(){
        System.out.println("\t\t\t\t\t=====================================");
        System.out.println("\t\t\t\t\t||        SEARCH FOOD BY NAME      ||");
        System.out.println("\t\t\t\t\t=====================================");

        while (true){
            String searchString = Input.getString("\t\t\tName of food: ",
                                                 "NAME MUST NOT NULL AND MUST NOT NUMERIC!!!");
            System.out.println("\t\t\t\t\t\t\t*********************");
            System.out.println("\t\t\t\t\t\t\t||      RESULT     ||");
            System.out.println("\t\t\t\t\t\t\t*********************");
            String format = "|%1$-10s|%2$-20s|%3$-20s|%4$-20s|%5$-20s|%6$-20s\n";
            System.out.format(format, "ID", "NAME", "WEIGHT", "TYPE", "PLACESTORE", "EXPIRED");
            System.out.println("-------------------------------------------------------------------------------------");

            boolean haveValue = false;
            for (Food food: listFood) {
                if (food.getFoodName().contains(searchString)){
                    haveValue = true;
                    System.out.println(food.toString());
                }
            }

            if (!haveValue){
                System.out.println("Name of food not exist!");
            }

            String exit = Input.getString("\tContinue Searching [y|n]: ",
                                          "PLEASE, TYPE [y|n]!!!");
            if (!exit.toUpperCase().equals("Y")){
                break;
            }
        }

        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }


    public void addFood(){
        System.out.println("\t\t\t\t\t=====================================");
        System.out.println("\t\t\t\t\t||        ADDING A NEW FOOD        ||");
        System.out.println("\t\t\t\t\t=====================================");

        while (true){
            Food food = new Food().inputInfoFood();
            if (!listFood.contains(food)){
                listFood.add(food);
                System.out.println("ADDING SUCCESS.");
                String exit = Input.getString("\tContinue Adding [y|n]: ",
                                               "PLEASE, TYPE [y|n] ");
                if (!exit.toUpperCase().equals("Y")){
                    break;
                }
            }else {
                System.out.println("FOODID WAS DUPLICATED. ADDING FAILED!!!");
                break;
            }
        }

        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void remove(){

        System.out.println("\t\t\t\t\t=====================================");
        System.out.println("\t\t\t\t\t||        DELETE FOOD BY ID        ||");
        System.out.println("\t\t\t\t\t=====================================");

        while (true){
            int id = Input.getInteger("\t\t\tFoodId to remove: ",
                    "FOODID MUST BE INTEGER NUMERIC GREATER THAN 0 (>0) ALSO THIS FIELD NOT NULL!!!");

            for (Food element: listFood){
                if (id == element.getFoodId()){
                    System.out.println(element.toString());
                    String confirmDel = Input.getString("\t\t\tAre you sure to remove this food [y|n]: ",
                                                        "PLEASE, TYPE [y|n]!!!");
                    // confirm delete
                    if (confirmDel.toUpperCase().equals("Y")){
                        listFood.remove(element);
                        System.out.println("FOOD: ID " + element.getFoodId() + " " +element.getFoodName()+ " WAS SUCCESSFULLY DELETED.");
                        System.out.println();
                        return;
                    } else {
                        System.out.println("DELETE CANCEL.");
                        return;
                    }
                }
            }
            System.out.println("DELETE FAILED: FOODID "+ id + " NOT FOUND.");
            break;
        };
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void displayListFood(){
        System.out.println("\t\t\t\t\t=====================================");
        System.out.println("\t\t\t\t\t||        INFORMATION FOOD         ||");
        System.out.println("\t\t\t\t\t=====================================");
        String format = "|%1$-10s|%2$-20s|%3$-20s|%4$-20s|%5$-20s|%6$-20s\n";
        System.out.format(format, "ID", "NAME", "WEIGHT", "TYPE", "PLACESTORE", "EXPIRED");
        System.out.println("-----------------------------------------------------------------------------------------");

        // Using lambda expression with Comparator to determine condition sorting
        Comparator<Food> comparator = (o1, o2) -> {
            return o2.getExpiredDate().compareTo(o1.getExpiredDate());
        };
        listFood.sort(comparator);
        for (Food food:listFood) {
            System.out.println(food.toString());
        }

        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void saveTextFile(){
        System.out.println("\t\t\t\t\t=====================================");
        System.out.println("\t\t\t\t\t||              SAVE               ||");
        System.out.println("\t\t\t\t\t=====================================");

        String nameFile = Input.getString("\t\t\tName of file: ",
                                         "NAMEFILE MUST NOT NULL AND MUST NOT NUMERIC!!!");
        nameFile = "./src/lab01/data/" + nameFile + ".txt";
        FileManagement fileManagement = new FileManagement(nameFile);
        fileManagement.saveListAsTextFile(listFood);


        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void exit(){
        System.out.println();
        System.out.println("=========================================================================================");
        System.out.println("||                                    BYE BYE                                          ||");
        System.out.println("=========================================================================================");
        System.exit(0);
    }
}
