package model;

public class PlayerName {
    private static final String DEALER_NAME = "딜러";
    private static final String DEALER_NAME_ERROR_MESSAGE = "딜러라는 이름은 사용할 수 없습니다.";

    private final String value;

    public PlayerName(String value) {
        value = value.trim();
        checkIsNotDealer(value);
        this.value = value;
    }

    private void checkIsNotDealer(String value) {
        if (value.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(DEALER_NAME_ERROR_MESSAGE);
        }
    }
}
