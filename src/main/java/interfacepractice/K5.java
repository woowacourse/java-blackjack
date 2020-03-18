package interfacepractice;

public class K5 extends Car {

    private static int FUEL_EFFICIENCY = 13;
    private static String NAME = "K5";

    public K5(int distance) {
        super(FUEL_EFFICIENCY, distance);
    }

    public String getName() {
        return NAME;
    }
}
