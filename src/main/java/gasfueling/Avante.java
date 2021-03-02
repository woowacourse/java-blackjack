package gasfueling;

public class Avante extends Car {

    public Avante(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return 15.0;
    }

    @Override
    String getName() {
        return "Avante";
    }
}
