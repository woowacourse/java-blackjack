package domain.blackjack;

public class BetAmount {
    private final int betAmount;

    public BetAmount(int betAmount) {
        validate(betAmount);
        this.betAmount = betAmount;
    }

    private void validate(int betAmount) {
        if (betAmount < 1000) {
            throw new IllegalArgumentException("배팅 금액으로 1000 미만의 값은 입력할 수 없습니다.");
        }
    }

    public double calculateProfit(WinStatus winStatus) {
        return betAmount * winStatus.getBetMultiplier();
    }
}
