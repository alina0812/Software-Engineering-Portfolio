package datalayer_test.model;

import lombok.Data;

@Data
public class Transmission {

    private int model_id;
    private String name;
    private int price;
    private int[] compatible_with;

}
