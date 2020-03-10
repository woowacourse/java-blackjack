package rentacar;

public class Avante extends Car {

    public Avante(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return 15;
    }

    @Override
    double getTripDistance() {
        return this.distance;
    }

    @Override
    String getName() {
        return "Avante";
    }
}
