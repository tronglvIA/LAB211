package lab02.service;

import lab02.model.Student;
import lab02.util.GFile;

import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> listStudent;

    public StudentManager() {
        listStudent = new ArrayList<>();
        loadData();
    }

    private void loadData(){
        /* Init a Path to save file. */
        String pathFile = "./src/lab02/data/student.dat";

        /* Init data of Vaccine. */
        ArrayList<Student> dataStudent = new ArrayList<>();
        dataStudent.add(new Student("SE150000", "Ngoc le"));
        dataStudent.add(new Student("SE150001", "Hoa Doan"));
        dataStudent.add(new Student("SE150002", "Diem Le"));
        dataStudent.add(new Student("IA150003", "Hung Dung"));
        dataStudent.add(new Student("IA150004", "Duc Hoa"));
        dataStudent.add(new Student("IA150005", "Van Teo"));

        /* Saving into binary file (.dat) && read data to from binary file. */
        GFile<Student> vaccineFileManager = new GFile<>(pathFile);
        vaccineFileManager.writeObject(dataStudent,"WRITE STUDENT-DATA TO FILE SUCCESSFULLY.");
        listStudent = vaccineFileManager.readObject("GET STUDENT-DATA TO FILE SUCCESSFULLY.");
    }

    // Get listStudent
    public ArrayList<Student> getListStudent() {
        return listStudent;
    }

    // Get student by id
    public Student getStudentById(String id){
        int posOfStudent = isStudentFPT(id);
        if (isStudentFPT(id) >= 0){
            return listStudent.get(posOfStudent);
        }
        return null;
    }

    /* Checking Does Student in list or not? (following ID)
       Because ID is the primary key so that existing only one in list.
    */
    private int isStudentFPT(String checkId){
        for (int index=0; index<listStudent.size(); index++) {
            if (listStudent.get(index).getStudentId().equalsIgnoreCase(checkId)){
                return index;
            }
        }
//        System.out.println("STUDENT " + checkId + "IS NOT IN FPT!");
        return -1;
    }

}
