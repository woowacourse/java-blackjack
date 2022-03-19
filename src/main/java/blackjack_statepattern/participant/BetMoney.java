package blackjack_statepattern.participant;

public class BetMoney {

    private final int amount;

    public BetMoney(int amount) {
        validateBetMoney(amount);
        this.amount = amount;
    }

    private void validateBetMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 음수가 될 수 없습니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
