package lab02.service;

import lab02.model.Student;
import lab02.util.GFile;
import lab02.util.Input;
import lab02.model.Injection;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class InjectedManager {
    private ArrayList<Injection> listInjection;
    private StudentManager studentManager;
    private VaccineManager vaccineManager;

    public InjectedManager(){
        listInjection = new ArrayList<>();
        studentManager = new StudentManager();
        vaccineManager = new VaccineManager();
        loadData();
    }

    private void loadData(){
        /* Init a Path to save file. */
        String pathFile = "./src/lab02/data/injection.dat";

        /* read data to from binary file. */
        GFile<Injection> injectionFileManager = new GFile<>(pathFile);
        listInjection = injectionFileManager.readObject("GET INJECTION-DATA FROM FILE SUCCESSFULLY.");
    }


    public void menu(){
        System.out.println();
        System.out.println("=========================================================================================");
        System.out.println("||                                      MENU                                           ||");
        System.out.println("=========================================================================================");
        System.out.println("||\t\t\t1. Show Information all students have been injected.                       ||");
        System.out.println("||\t\t\t2. Add Student's injected information.                                     ||");
        System.out.println("||\t\t\t3. Updating information of students vaccine injected.                      ||");
        System.out.println("||\t\t\t4. Delete information of students vaccine injected.                        ||");
        System.out.println("||\t\t\t5. Searching injected information by StudentID.                            ||");
        System.out.println("||\t\t\t6. Save File (./src/lab02/data/injection.dat).                             ||");
        System.out.println("||\t\t\t7. Quit.                                                                   ||");
        System.out.println("=========================================================================================");
    }


    /*Show Information all students have been injected.*/
    public void displayInjectedInfo(){
        System.out.println("\t\t\t\t\t=====================================");
        System.out.println("\t\t\t\t\t||         STUDENT INJECTED        ||");
        System.out.println("\t\t\t\t\t=====================================");
        Input.header();

        if ( listInjection != null){
            for (Injection injected: listInjection) {
                System.out.println(injected.toString());
            }
        } else {
            System.out.println("NO ONE STUDENT GETS INJECTED!!!");
        }
    }

    /*Add Student's injected information.
    *       -private inputInfoFirst()
    *       -private isContainsStudentId() */
    public void addInjection(){
        System.out.println("\t\t\t\t\t=====================================");
        System.out.println("\t\t\t\t\t||       ADDING A NEW INJECTED     ||");
        System.out.println("\t\t\t\t\t=====================================");

        while(true){
            /* Init and injection information input. */
            Injection injection = inputInfoFirstInjection();

            /* Checking student is FPTer by studentID && listInjection not duplicate studentID;
               listInjection duplicate injectionID && VaccineId is valid in list vaccineData */
            if (studentManager.getStudentById(injection.getStudentId()) != null && !injectedContainStudentID(injection.getStudentId())){
                if (!listInjection.contains(injection) && vaccineManager.isValid(injection.getVaccineId())){
                    listInjection.add(injection);
                    System.out.println("ADDING SUCCESSFULLY.");
                } else {
                    if(listInjection.contains(injection)){
                        System.out.println("ADDING FAILED ==> DUPLICATE injectedID!!!");
                    } else{
                        System.out.println("ADDING FAILED ==> VACCINE '" + injection.getVaccineId() + "' IS NOT IN LIST vaccine.dat!!!");
                    }
                }
            } else {
                if (studentManager.getStudentById(injection.getStudentId()) == null){
                    System.out.println("ADDING FAILED ==> STUDENT '" + injection.getStudentId() + "' IS NOT IN FPT!!!" );
                } else{
                    System.out.println("ADDING FAILED ==> STUDENT '" + injection.getStudentId() + "' EXISTED IN LIST INJECTION - " +
                                                                                            "YOU SHOULD CHOICE UPDATE FUNCTION !!!" );
                }
            }

            /* Confirm continues or back to menu */
            if (!Input.confirm("Do you want to continue? [Y|N]: ")){
                break;
            }
        }

    }

    private Injection inputInfoFirstInjection(){
        Injection result =  new Injection();

        result.setStudentId(Input.getString("\t\t\tStudentID: ",
                "StudentID MUST BE NOT NULL!!!"));

        result.setInjectionId(Input.getString("\t\t\tInjectionID: ",
                "InjectionID MUST NOT NULL!!!"));

        result.setVaccineId(Input.getString("\t\t\tVaccineID: ",
                "VaccineID MUST BE NOT NULL!!!"));

        result.setFirstPlace(Input.getString("\t\t\tFirst Place Injected: ",
                "FirstPlace's Injected MUST BE NOT NULL!!!"));

        result.setFirstDate(Input.getDate("\t\t\tFirst Injected Date (DD/MM/YYYY): ", LocalDate.now()));
        return result;
    }

    private boolean injectedContainStudentID(String studentId){
        for(Injection injection: listInjection){
            if(injection.getStudentId().equalsIgnoreCase(studentId)){
                return true;
            }
        }
        return false;
    }


    /* Updating Injected information
    *           -private getInjectionById()
    *           -private isSecondInjection()
    *           -private updateInfoSecondInjection() */
    public void updateInjection(){
        System.out.println("\t\t\t\t\t=====================================");
        System.out.println("\t\t\t\t\t||         UPDATE INJECTION        ||");
        System.out.println("\t\t\t\t\t=====================================");
        // injectionID input.
        String injectionID = Input.getString("\t\t\tInjectionID: ",
                                             "InjectionID MUST NOT NULL!!!");

        // Show info of object - console
        Injection injection = getInjectionById(injectionID);
        if (injection != null){
            Input.header();
            System.out.println(injection.toString());
            LocalDate secondDate = Input.getDate("\t\t\tSecond Injected Date (DD/MM/YYYY): ", injection.getFirstDate());

            long distanceTime = ChronoUnit.DAYS.between(injection.getFirstDate(), secondDate);
            if (distanceTime >= 28){
                if (isSecondInjected(injection)){
                    updateInfoSecondInjection(injection, secondDate);
                    System.out.println("UPDATE SUCCESSFULLY.");
                }else {
                    System.out.println("UPDATE FAILED!!! ==> STUDENT HAS COMPLETED 2 INJECTIONS.");
                }
            } else{
                System.out.println("FIRST INJECTED IS NOT ENOUGH DISTANCE-TIME ==> COME BACK AFTER " + (28-distanceTime) + " DAYS");
            }
        }else{
            System.out.println("UPDATE FAILED ==> injectionID: '" + injectionID + "' DOES NOT EXIST!!!");
        }
    }

    private void updateInfoSecondInjection(Injection injection, LocalDate secondDate){
        System.out.println("\t\t\tVaccine: " + injection.getVaccineId());
        injection.setSecondDate(secondDate);
        injection.setSecondPlace(Input.getString("\t\t\tSecond Place Injected: ",
                "SecondPlace's Injected MUST BE NOT NULL!!!"));
    }

    /* Checking isFistInjected && isSecondInjected */
    private boolean isSecondInjected(Injection injection) {
        return (injection.getSecondPlace() == null && injection.getSecondDate() == null);
    }

    /* Getting Injection object by injectionIdID */
    private Injection getInjectionById(String injectionId){
        for (Injection injection: listInjection) {
            if(injection.getInjectionId().equalsIgnoreCase(injectionId)){
                return injection;
            }
        }
        return null;
    }


    /*DELETING INFORMATION OF STUDENTS VACCINE INJECTED */
    public void removeInjection(){
        System.out.println("\t\t\t\t\t=====================================");
        System.out.println("\t\t\t\t\t||         REMOVE INJECTION        ||");
        System.out.println("\t\t\t\t\t=====================================");

        String injectionID = Input.getString("\t\t\tInjectionID: ","InjectionID MUST NOT NULL!!!");
        Injection injection = getInjectionById(injectionID);
        if (injection != null){
            Input.header();
            System.out.println(injection.toString());
            if (Input.confirm("Are you sure to DELETE this Injection? [Y|N]: ")){
                listInjection.remove(getInjectionById(injectionID));
                System.out.println("DELETE '" + injectionID + "' SUCCESSFULLY.");
            } else{
                System.out.println("DELETE CANCEL.");
            }
        } else {
            System.out.println("DELETE FAILED ==> '" + injectionID + "' NOT FOUND!!!");
        }
    }

    /* SEARCH INJECTION BY STUDENT-ID*/
    public void searchInjection(){
        System.out.println("\t\t\t\t\t=====================================");
        System.out.println("\t\t\t\t\t||         SEARCH INJECTION        ||");
        System.out.println("\t\t\t\t\t=====================================");
        while (true){
            String searchStudentId = Input.getString("\t\t\tStudentID: ", "StudentID MUST NOT NULL!!!");
            System.out.println("\t\t\t\t\t\t\t*********************");
            System.out.println("\t\t\t\t\t\t\t||      RESULT     ||");
            System.out.println("\t\t\t\t\t\t\t*********************");
            Input.header();

            /*RETURN CONSOLE RESULTS*/
            boolean haveValue = false;
            for (Injection injection: listInjection) {
                if (injection.getStudentId().equalsIgnoreCase(searchStudentId)){
                    haveValue = true;
                    System.out.println(injection.toString());
                }
            }
            if (!haveValue){
                System.out.println("==> NOT EXIST INJECTION OF Student '" + searchStudentId + "'");
            }

            /*CONFIRM CONTINUES OR BACK TO MENU*/
            if (!Input.confirm("Do you want to continue? [Y|N]: ")){
                break;
            }
        }
    }

    /*SAVING TO FILE(.DAT)*/
    public void saveObject(){
        String pathFile = "./src/lab02/data/injection.dat";
        GFile<Injection> injectionGFile = new GFile<>(pathFile);
        injectionGFile.writeObject(listInjection, "WRITE INJECTION-DATA SUCCESSFULLY.");
    }

    /*QUIZ THE PROGRAM*/
    public void exit(){
        System.out.println();
        System.out.println("=========================================================================================");
        System.out.println("||                                    BYE BYE                                          ||");
        System.out.println("=========================================================================================");
        System.exit(0);
    }

}
