package rentcompany;

public class Avante extends Car {

    public Avante(int tripDistance) {
        super(15, tripDistance, "Avante");
    }

    @Override
    double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    double getTripDistance() {
        return tripDistance;
    }

    @Override
    String getName() {
        return name;
    }
}
