package dataAccessLayer.model;

public class Seat {

    private int model_id;
    private String name;
    private int price;
    private int[] compatible_with;

    public int getModel_id(){
        return  model_id;
    }

    public String getName(){
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int[] getCompatible_with() {
        return compatible_with;
    }
}
