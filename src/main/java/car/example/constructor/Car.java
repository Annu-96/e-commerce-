package car.example.constructor;

public class Car {
    private inSpecification inSpecification;

    public Car(inSpecification inSpecification) {
        this.inSpecification = inSpecification;
    }

    public void displayDetails(){
        System.out.println("Car Details:" + inSpecification.toString());
    }
}
