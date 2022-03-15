package fuel;

public class K5 extends AbstractCar implements Car { ;

    public K5(double distance) {
        this.fuelEfficiency = 13;
        this.distance = distance;
    }

    @Override
    public String getName() {
        return "K5";
    }
}
