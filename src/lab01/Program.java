package lab01;


import lab01.service.FoodManagement;
import lab01.util.Input;

public class Program {
    public static void main(String[] args) {
        int option;
        FoodManagement foodManagement = new FoodManagement();
        while(true){
            foodManagement.menu();
            option = Input.getInteger("Your Choice: ",
                                      "MUST BE NUMERIC IN RANGE(1,6) ALSO THIS FIELD NOT NULL!!!");
            switch (option){
                case 1:
                    foodManagement.addFood();
                    break;
                case 2:
                    foodManagement.searchByName();
                    break;
                case 3:
                    foodManagement.remove();
                    break;
                case 4:
                    foodManagement.displayListFood();
                    break;
                case 5:
                    foodManagement.saveTextFile();
                    break;
                case 6:
                    foodManagement.exit();
                    break;
                default:
                    System.out.println("OPTION OUT OF RANGE(1,6), CHOICE AGAIN!");
            }

        }
    }
}
