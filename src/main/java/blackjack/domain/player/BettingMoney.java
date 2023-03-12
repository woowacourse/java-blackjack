package blackjack.domain.player;

public class BettingMoney {
    private final int value;

    public BettingMoney(String value) {
        validate(value);
        this.value = Integer.parseInt(value);
    }

    private void validate(String value) {
        try {
            int newValue = Integer.parseInt(value);
            if (newValue < 0 || newValue % 100 != 0) {
                throw new IllegalArgumentException("양의 정수(100원 단위)만 입력이 가능합니다.");
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("양의 정수(100원 단위)만 입력이 가능합니다.");
        }
    }
}
