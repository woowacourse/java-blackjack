package domain;

public class Money {
    private final int betAmount;

    public Money(int betAmount) {
        validateRange(betAmount);
        this.betAmount = betAmount;
    }

    private void validateRange(int betAmount) {
        if (betAmount < 1000 || betAmount > 300000) {
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
