package rentcompany;

public class Avante implements Car {

    private final int distance;

    public Avante(int distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return 15;
    }

    @Override
    public double getTripDistance() {
        return distance;
    }

    @Override
    public String getName() {
        return "Avante";
    }
}
