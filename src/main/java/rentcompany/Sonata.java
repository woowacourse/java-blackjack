package rentcompany;

public class Sonata extends Car {
    
    public Sonata(int tripDistance) {
        this.tripDistance = tripDistance;
    }
    
    @Override
    double getDistancePerLiter() {
        return 10;
    }
    
    @Override
    double getTripDistance() {
        return tripDistance;
    }
    
    @Override
    String getName() {
        return "Sonata";
    }
}
