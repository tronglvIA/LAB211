package lab02.service;

import lab02.model.Vaccine;
import lab02.util.GFile;

import java.util.ArrayList;

public class VaccineManager {
    private ArrayList<Vaccine> listVaccine;

    public VaccineManager() {
        listVaccine = new ArrayList<>();
        loadData();
    }

    private void loadData(){
        /* Init a Path to save file. */
        String pathFile = "./src/lab02/data/vaccine.dat";

        /* Init data of Vaccine. */
        ArrayList<Vaccine> dataVaccine = new ArrayList<>();
        dataVaccine.add(new Vaccine("Covid-V001", "AstraZeneca"));
        dataVaccine.add(new Vaccine("Covid-V002", "SPUTNIK V"));
        dataVaccine.add(new Vaccine("Covid-V003", "Vero Cell"));
        dataVaccine.add(new Vaccine("Covid-V004", "Pfizer"));
        dataVaccine.add(new Vaccine("Covid-V005", "Moderna"));

        /* Saving into binary file (.dat) && read data to from binary file. */
        GFile<Vaccine> vaccineFileManager = new GFile<>(pathFile);
        vaccineFileManager.writeObject(dataVaccine,"WRITE VACCINE-DATA FROM FILE SUCCESSFULLY.");
        listVaccine = vaccineFileManager.readObject("GET VACCINE-DATA FROM FILE SUCCESSFULLY.");
    }

    // Get listVaccine
    public ArrayList<Vaccine> getListVaccine() {
        return listVaccine;
    }

    // Checking Vaccine by ID.
    public boolean isValid(String vaccineId){
        for (Vaccine vaccine: listVaccine) {
            if (vaccine.getVaccineId().equalsIgnoreCase(vaccineId)){
                return true;
            }
        }
        return false;
    }
}
