package fuel;

public abstract class Car {

    private final double distancePerLiter;
    private final double tripDistance;
    private final String name;

    public Car(double distancePerLiter, double tripDistance, String name) {
        this.distancePerLiter = distancePerLiter;
        this.tripDistance = tripDistance;
        this.name = name;
    }

    /**
     * 리터당 이동 거리. 즉, 연비
     */
    double getDistancePerLiter() {
        return distancePerLiter;
    }

    /**
     * 여행하려는 거리
     */
    double getTripDistance() {
        return tripDistance;
    }

    /**
     * 차종의 이름
     */
    String getName() {
        return name;
    }

    /**
     * 주입해야할 연료량을 구한다.
     */
    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
