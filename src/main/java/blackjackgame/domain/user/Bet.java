package blackjackgame.domain.user;

public class Bet {
    private final int amount;

    public Bet(int amount) {
        validateBetAmount(amount);
        this.amount = amount;
    }

    private void validateBetAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 1원 이상입니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
