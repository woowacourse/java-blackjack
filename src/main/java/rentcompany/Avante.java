package rentcompany;

public class Avante extends Car {

    public Avante(double tripDistance) {
        this.distancePerLiter = 15.0;
        this.tripDistance = tripDistance;
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
        return "Avante";
    }
}
