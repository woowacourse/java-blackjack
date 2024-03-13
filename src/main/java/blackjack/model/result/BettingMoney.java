package blackjack.model.result;

public final class BettingMoney {
    private static final BettingMoney ZERO_MONEY = new BettingMoney(0);

    private final double amount;

    public BettingMoney(final double amount) {
        this.amount = amount;
    }

    public BettingMoney fixByMatchResult(final MatchResult matchResult) {
        if (matchResult == MatchResult.LOSE) {
            return ZERO_MONEY;
        } else if (matchResult == MatchResult.WIN) {
            return this.plus(this);
        }
        return this;
    }

    public BettingMoney plus(final BettingMoney otherMoney) {
        return new BettingMoney(amount + otherMoney.amount);
    }

    public BettingMoney minus(final BettingMoney otherMoney) {
        return new BettingMoney(amount - otherMoney.amount);
    }

    public double getAmount() {
        return amount;
    }
}
