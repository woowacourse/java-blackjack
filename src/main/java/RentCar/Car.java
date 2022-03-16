package RentCar;

public abstract class Car {

    private static final String DELIMITER = " : ";
    private static final String UNIT = "리터";
    private double distance;
    private String name;


    public Car(double distance, String name) {
        this.distance = distance;
        this.name = name;
    }

    /**
     * 리터당 이동 거리. 즉, 연비
     */
    abstract double getDistancePerLiter();

    /**
     * 여행하려는 거리
     */
    public double getTripDistance() {
        return this.distance;
    }

    /**
     * 차종의 이름
     */
    public String getName() {
        return this.name;
    }

    /**
     * 주입해야할 연료량을 구한다.
     */
    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    public String getData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.name)
                .append(DELIMITER)
                .append(getChargeQuantity())
                .append(UNIT);
        return stringBuilder.toString();
    }
}
