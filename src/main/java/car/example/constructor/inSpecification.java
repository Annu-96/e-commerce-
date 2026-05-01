package car.example.constructor;

public class inSpecification {

    private String make;
    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String toString() {
        return "CarSpecification{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
