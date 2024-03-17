package blackjack.domain.game;

public class BettingMoney {

    private final int amount;

    public BettingMoney(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("배팅 액수는 양수여야 합니다.");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
