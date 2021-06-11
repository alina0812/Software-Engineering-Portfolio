package datalayer_test;

import javax.json.JsonArray;

public class Seat {

    private int model_id;
    private String name;
    private int price;
    private int[] compatible_with = new int[4];

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int[] getCompatible_with() {
        return compatible_with;
    }

    public void setCompatible_with(JsonArray compatible_with) {
        for(int i = 0; i < compatible_with.size(); i++) {
            this.compatible_with[i] = compatible_with.getInt(i);
        }
    }
}
