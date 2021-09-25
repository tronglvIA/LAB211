package lab02.model;

import lab01.model.Food;
import lab01.util.Input;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Injection implements Serializable {
    private String injectionId;
    private String studentId;
    private String vaccineId;
    private LocalDate firstDate = null;
    private String firstPlace = null;
    private LocalDate secondDate = null;
    private String secondPlace = null;

    public Injection() {
    }

    public Injection(String injectionId) {
        this.injectionId = injectionId;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Injection) {
            return this.injectionId.equals(((Injection) obj).getInjectionId());
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String format = "|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s\n";
        return String.format( format, injectionId, studentId, vaccineId,
                                      (firstDate != null ? firstDate.format(formatter) : ""),
                                      (firstPlace != null ? firstPlace : ""),
                                      (secondDate != null ? secondDate.format(formatter) : ""),
                                      (secondPlace != null ? secondPlace : ""));
    }


    /* Getter and Setters */

    public String getInjectionId() {
        return injectionId;
    }

    public void setInjectionId(String injectionId) {
        this.injectionId = injectionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public LocalDate getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public LocalDate getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(LocalDate secondDate) {
        this.secondDate = secondDate;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }
}