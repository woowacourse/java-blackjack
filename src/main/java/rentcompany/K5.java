package rentcompany;

public class K5 extends Car {
    
    public K5(int tripDistance) {
        this.tripDistance = tripDistance;
    }
    
    @Override
    double getDistancePerLiter() {
        return 13;
    }
    
    @Override
    double getTripDistance() {
        return tripDistance;
    }
    
    @Override
    String getName() {
        return "K5";
    }
}
