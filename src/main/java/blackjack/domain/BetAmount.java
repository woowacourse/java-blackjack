package blackjack.domain;

public class BetAmount {

    private static final int MIN_BET_AMOUNT = 1_000;
    private static final int MAX_BET_AMOUNT = 100_000;

    private int value;

    public BetAmount() {
        this.value = 0;
    }

    public void initialize(int value) {
        validateAmount(value);
        this.value = value;
    }

    public void initializeDealer(int value) {
        this.value = value;
    }

    private static void validateAmount(int value) {
        if (value < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 1,000원 이상부터 가능합니다.");
        }
        if (value > MAX_BET_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 10만원까지만 가능합니다.");
        }
    }

    public void multiple(double times) {
        this.value *= times;
    }

    public int getValue() {
        return value;
    }
}
