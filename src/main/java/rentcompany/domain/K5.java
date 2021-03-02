package rentcompany.domain;

public class K5 extends Car{
    private final String name = "K5";
    private final double tripDistance;
    private final double distancePerLiter = 13;

    public K5(int tripDistance) {
        this.tripDistance = tripDistance;
    }
}
