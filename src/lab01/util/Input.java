package lab01.util;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Scanner;


public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    // get a Integer Number valid
    public static int getInteger(String message, String notification){
        int integerNumber;
        while (true){
            try{
                System.out.print(message);
                integerNumber = Integer.parseInt(scanner.nextLine());
                if (integerNumber > 0){
                    return integerNumber;
                } else{
                    throw new Exception();
                }
            }catch (Exception check){
                System.out.println(notification);
//                System.out.println("MUST BE NUMERIC GREATER THAN 0 (>0) ALSO THIS FIELD NOT NULL!!!");
            }
        }
    }

    // get a Double Number valid
    public static double getDouble(String message, String notification){
        double doubleNumber;
        while(true){
            try{
                System.out.print(message);
                doubleNumber = Double.parseDouble(scanner.nextLine());
                if (doubleNumber > 0){
                    return doubleNumber;
                } else{
                    throw new Exception();
                }
            }catch (Exception check){
                System.out.println(notification);
//                System.out.println("MUST BE NUMERIC GREATER THAN 0 (>0) ALSO THIS FIELD NOT NULL!!!");
            }
        }
    }

    //get a String valid
    public static String getString(String message, String notification){
        String inputString;
        while (true){
            System.out.print(message);
            inputString = scanner.nextLine();
            if(inputString.trim().length() > 0){
                break;
            } else {
                System.out.println(notification);
//                System.out.println("THIS FIELD MUST NOT BE NUMBER AND NOT NULL!!!");
            }
        }
        return inputString.trim();
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


    //get a String valid
    public static LocalDate getDate(String message){
        String dateInput;
        LocalDate parsingDate = null;
       LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("d/M/uuuu");
        while(true){
            try {
                System.out.print(message);
                dateInput = scanner.nextLine();
                if (isValid(dateInput)){
                    parsingDate = LocalDate.parse(dateInput, formatDate);
                    if(parsingDate.isAfter(currentDate) || parsingDate.equals(currentDate)){
                        return parsingDate;
                    } else {
                        System.out.println("DAYTIME MUST BE AFTER OR IS " + currentDate.format(formatDate));
                    }
                } else {
                    throw new Exception();
                }
            }catch (Exception exception){
                System.out.println("DAY, MOUTH, YEAR MUST BE VALID.");
            }
        }
    }

    // Checking Valid day
    private static boolean isValid(String date){
        boolean valid = false;
        try{
            LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT));
            valid = true;
        }catch (DateTimeException e){
            valid =false;
        }
        return valid;
    }

}
