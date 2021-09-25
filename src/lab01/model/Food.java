package lab01.model;


import lab01.util.Input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Food {
    private int foodId;
    private String foodName;
    private double weight;
    private String type;
    private String place;
    private LocalDate expiredDate;

//===============================Constructor=============================//
    public Food() {
    }

    public Food(int foodId, String foodName, float weight, String type, String place, LocalDate expiredDate) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.expiredDate = expiredDate;
    }

//Override equals (Validation not duplicate ID)
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Food){
            Food food = (Food) obj;
            return this.foodId == food.foodId;
        }
        return super.equals(obj);
    }
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String format = "|%-10d|%-20s|%-20.2f|%-20s|%-20s|%-20s\n";
        return String.format( format, foodId, foodName, weight,type,place,expiredDate.format(dateTimeFormatter));
    }

    public Food inputInfoFood(){
        Food result = new Food();
        result.setFoodId(Input.getInteger("\t\t\tFood ID (int): ",
                                         "ID MUST BE NUMERIC GREATER THAN 0 (>0) ALSO THIS FIELD NOT NULL!!!"));

        result.setFoodName(Input.getString("\t\t\tFood Name: ",
                                            "NAME MUST NOT NULL AND MUST NOT NUMERIC!!!"));

        result.setWeight(Input.getDouble("\t\t\tWeight: ",
                                        "MUST BE NUMERIC GREATER THAN 0 (>0) ALSO THIS FIELD NOT NULL!!!"));

        result.setType(Input.getString("\t\t\tType of food: ",
                                      "TYPE MUST NOT NULL AND MUST NOT NUMERIC!!!"));

        result.setPlace(Input.getString("\t\t\tPlace store this food: ",
                                         "PLACE MUST NOT NULL AND MUST NOT NUMERIC!!!"));

        result.setExpiredDate(Input.getDate("\t\t\tExpiredDate (DD/MM/YYYY): "));
        return result;
    }

//===================================getters and setters===============================//
    public int getFoodId() {
        return foodId;
    }

    private void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
}
