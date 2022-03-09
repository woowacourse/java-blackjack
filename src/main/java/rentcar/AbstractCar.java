package rentcar;

public abstract class AbstractCar implements Car {

    final double tripDistance;

    public AbstractCar(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    /**
     * 주입해야할 연료량을 구한다.
     */
    @Override
    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
