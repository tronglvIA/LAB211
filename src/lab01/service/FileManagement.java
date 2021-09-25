package lab01.service;

import lab01.model.Food;
import java.io.*;
import java.util.ArrayList;

public class FileManagement{
    private File file;

    public FileManagement(String path) {
      this.file = new File(path);
    }

    public void saveListAsTextFile(ArrayList<Food> listFood) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Food food: listFood) {
                bufferedWriter.write(food.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("SAVE FILE SUCCESSFULLY.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
