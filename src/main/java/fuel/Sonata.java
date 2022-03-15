package fuel;

public class Sonata extends AbstractCar implements Car {

    public Sonata(double distance) {
        this.fuelEfficiency = 10;
        this.distance = distance;
    }

    @Override
    public String getName() {
        return "Sonata";
    }
}
