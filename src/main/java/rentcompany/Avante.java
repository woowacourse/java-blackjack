package rentcompany;

public class Avante extends Car {
    
    public Avante(int tripDistance) {
        this.tripDistance = tripDistance;
    }
    
    @Override
    double getDistancePerLiter() {
        return 15;
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
