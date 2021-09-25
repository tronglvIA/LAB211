package lab02.model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class Student implements Serializable {
    private String studentId;
    private String studentName;

    public Student(){
    }

    public Student(String studentId) {
        this.studentId = studentId;
    }

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student){
            Student student = (Student) obj;
            return this.getStudentName().equalsIgnoreCase(student.getStudentName());
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        String format = "|%-10d|%-20s|";
        return String.format( format, studentId,studentName);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
