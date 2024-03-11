package domain.blackjack;

public class BetAmount {
    private final int betAmount;

    public BetAmount(int betAmount) {
        validate(betAmount);
        this.betAmount = betAmount;
    }

    private void validate(int betAmount) {
        if (betAmount < 1000) {
            throw new IllegalArgumentException("배팅 금액은 1000원 이상부터 가능합니다.");
        }
    }

    public int getBetAmount() {
        return betAmount;
    }
}
