package domain;

public class Money {
    private static final int MINIMUM_BET_AMOUNT = 1_000;
    private static final int MAXIMUM_BET_AMOUNT = 300_000;

    private final int betAmount;

    public Money(int betAmount) {
        validateRange(betAmount);
        this.betAmount = betAmount;
    }

    private void validateRange(int betAmount) {
        if (betAmount < MINIMUM_BET_AMOUNT || betAmount > MAXIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 1,000원 이상 300,000원 이하로 입력해주세요.");
        }
    }

    public int blackjack() {
        return (int) (betAmount * 1.5);
    }

    public int bust() {
        return -betAmount;
    }

    public int win() {
        return betAmount;
    }

    public int lose() {
        return -betAmount;
    }

    public int draw() {
        return 0;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
