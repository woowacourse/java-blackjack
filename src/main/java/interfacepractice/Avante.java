package interfacepractice;

public class Avante extends Car {

    private static int FUEL_EFFICIENCY = 15;
    private static String NAME = "Avante";

    public Avante(int distance) {
        super(FUEL_EFFICIENCY, distance);
    }

    public String getName() {
        return NAME;
    }
}
