package fuel_injection;

public class Avante extends Car {

    public Avante(int distance) {
        super("Avante", 15, distance);
    }

    public static void main(String[] args) {
        Avante avante = new Avante(150);
        System.out.println("avante는... " + avante.getChargeQuantity() + "L 의 연료가 필요합니다!");
    }

    @Override
    double getDistancePerLiter() {
        return efficiency;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return name;
    }
}
