package lab02.model;
import java.io.Serializable;

public class Vaccine implements Serializable {
    private String vaccineId;
    private String vaccineName;

    public Vaccine() {
    }
    public Vaccine(String vaccineId) {
        this.vaccineId =vaccineId;
    }

    public Vaccine(String vaccineId, String vaccineName) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Vaccine){
            Vaccine vaccine = (Vaccine) obj;
            return this.vaccineId.equals(vaccine.getVaccineId());
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        String format = "|%-10d|%-20s|";
        return String.format( format, vaccineId,vaccineName);
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
}
