package blackjack.domain;

public class BettingAmount {
    private final int bettingAmount;

    public BettingAmount(int bettingAmount) {
        if (bettingAmount < 0) {
            throw new IllegalArgumentException("배팅 금액은 0원 이상이어야 합니다.");
        }
        this.bettingAmount = bettingAmount;
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
