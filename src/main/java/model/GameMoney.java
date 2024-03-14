package model;

public class GameMoney {
    private final int value;

    public GameMoney(int value) {
        validateGameMoneyOverZero(value);
        this.value = value;
    }

    private void validateGameMoneyOverZero(int value) {
        if(value < 1) {
            throw new IllegalArgumentException("게임 배팅 금액은 0보다 커야 합니다.");
        }
    }
}
