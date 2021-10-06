package lab02;

import lab02.util.Input;
import lab02.service.InjectedManager;

public class Program {
    public static void main(String[] args) {
        int option;
        InjectedManager injectedManager = new InjectedManager();
        while(true){
            injectedManager.menu();
            option = Input.getInteger("Your Choice: ",
                                      "MUST BE NUMERIC INTEGER IN RANGE(1,6) ALSO THIS FIELD NOT NULL!!!");
            switch (option){
                case 1:
                    injectedManager.displayInjectedInfo();
                    break;
                case 2:
                    injectedManager.addInjection();
                    break;
                case 3:
                    injectedManager.updateInjection();
                    break;
                case 4:
                    injectedManager.removeInjection();
                    break;
                case 5:
                    injectedManager.searchInjection();
                    break;
                case 6:
                    injectedManager.saveObject();
                    break;
                case 7:
                    injectedManager.exit();
                    break;
                default:
                    System.out.println("OPTION OUT OF RANGE(1,6), CHOICE AGAIN!");
            }

        }
    }
}
