package blackjack.domain;

public class BettingMoney {
    private static final int MIN_BETTING_MONEY_BOUND = 1_000;
    private static final int MAX_BETTING_MONEY_BOUND = 100_000_000;
    private static final int NO_PROFIT = 0;
    private static final double BLACKJACK_WIN_PROFIT_RATE = 1.5;

    private final int value;

    public BettingMoney(int value) {
        validate(value);
        this.value = value;
    }

    public BettingMoney() {
        this(MIN_BETTING_MONEY_BOUND);
    }

    private void validate(int value) {
        if (!(MIN_BETTING_MONEY_BOUND <= value && value <= MAX_BETTING_MONEY_BOUND)) {
            throw new IllegalArgumentException("배팅 금액은 1000원 이상, 1억원 이하여야 합니다.");
        }
    }

    public int getProfit(ResultTypeNew resultType) {
        if (resultType == ResultTypeNew.LOSS) {
            return -value;
        }
        if (resultType == ResultTypeNew.DRAW) {
            return NO_PROFIT;
        }
        if (resultType == ResultTypeNew.WIN_WITH_BLACKJACK) {
            return (int) ((double) value * BLACKJACK_WIN_PROFIT_RATE);
        }
        return value;
    }
}
