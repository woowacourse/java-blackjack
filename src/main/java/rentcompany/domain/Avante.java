package rentcompany.domain;

public class Avante extends Car {
    private final String name = "Avante";
    private final double tripDistance;
    private final double distancePerLiter = 15;

    public Avante(int tripDistance) {
        this.tripDistance = tripDistance;
    }
}
