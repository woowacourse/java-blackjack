package gasfueling;

public class Sonata extends Car {

    public Sonata(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return 10.0;
    }

    @Override
    String getName() {
        return "Sonata";
    }
}
