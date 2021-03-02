package rentcompany.domain;

public class Sonata extends Car{
    private final String name = "Sonata";
    private final double tripDistance;
    private final double distancePerLiter = 10;

    public Sonata(int tripDistance) {
        this.tripDistance = tripDistance;
    }
}
