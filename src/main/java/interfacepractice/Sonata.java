package interfacepractice;

public class Sonata extends Car {

    private static int FUEL_EFFICIENCY = 10;
    private static String NAME = "Sonata";

    public Sonata(int distance) {
        super(FUEL_EFFICIENCY, distance);
    }

    public String getName() {
        return NAME;
    }
}
