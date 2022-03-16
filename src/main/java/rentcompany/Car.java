package rentcompany;

public abstract class Car {

    protected final double distance;

    public Car(double distance) {
        validateDistance(distance);
        this.distance = distance;
    }

    private void validateDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("거리는 0 초과하여야 합니다.");
        }
    }

    abstract public double getDistance();

    abstract public double getDistancePerLiter();

    abstract public String getName();

    public double getChargeQuantity() {
        return getDistance() / getDistancePerLiter();
    }
}
