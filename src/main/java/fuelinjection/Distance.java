package fuelinjection;

public class Distance {
    private static final String ERROR_MIN_DISTANCE = "[ERROR] 거리는 0이하 일 수 없습니다.";
    private static final int MIN_DISTANCE = 0;

    private final int value;

    public Distance(int value) throws IllegalArgumentException {
        checkValue(value);
        this.value = value;
    }

    private void checkValue(int value) {
        if (value <= MIN_DISTANCE) {
            throw new IllegalArgumentException(ERROR_MIN_DISTANCE);
        }
    }
}
