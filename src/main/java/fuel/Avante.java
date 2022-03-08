package fuel;

public class Avante extends AbstractCar {

    public Avante(double distance) {
        this.fuelEfficiency = 15;
        this.distance = distance;
    }

    @Override
    public String getName() {
        return "Avante";
    }
}
