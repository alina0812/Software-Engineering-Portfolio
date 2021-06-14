package datalayer_test.model;

import java.util.ArrayList;
import lombok.Data;

@Data
public class ConfigurationDTO {

  private ArrayList<Model> models;
  private ArrayList<Engine> engines;
  private ArrayList<Transmission> transmissions;
  private ArrayList<Seat> seats;
}
