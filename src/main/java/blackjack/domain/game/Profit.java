package blackjack.domain.game;

public class Profit {

    private final int amount;

    private Profit(int amount) {
        this.amount = amount;
    }

    public static Profit of(BettingMoney bettingMoney, PlayerState playerState) {
        return new Profit((int) (bettingMoney.getAmount() * playerState.getMultiple()));
    }

    public Profit toNegative() {
        return new Profit(-amount);
    }

    public Profit sum(Profit profit) {
        return new Profit(amount + profit.getAmount());
    }

    public int getAmount() {
        return amount;
    }
}
