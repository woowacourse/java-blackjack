package domain.blackjack;

public class BetAmount {

    private static final int MIN_BET_AMOUNT = 1000;

    private final int betAmount;

    public BetAmount(int betAmount) {
        validate(betAmount);
        this.betAmount = betAmount;
    }

    private void validate(int betAmount) {
        if (betAmount < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액으로 1000 미만의 값은 입력할 수 없습니다.");
        }
    }

    public double calculateProfit(WinStatus winStatus) {
        return betAmount * winStatus.getBetMultiplier();
    }
}
