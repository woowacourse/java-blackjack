package blackjack.domain.money;

public class BetAndProfit {

    // TODO: 불변 객체로 만들기
    private static final int INITIAL_PROFIT = 0;
    private static final String NEGATIVE_BET_MONEY_EXCEPTION_MESSAGE = "베팅 금액은 음수일 수 없습니다.";

    private final Money betMoney;
    private Money profitMoney;

    public BetAndProfit(Money betMoney) {
        this.betMoney = betMoney;
        this.profitMoney = Money.from(INITIAL_PROFIT);
    }

    public static BetAndProfit from(Money betMoney) {
        validateNotNegativeBetMoney(betMoney);
        return new BetAndProfit(betMoney);
    }

    private static void validateNotNegativeBetMoney(Money betMoney) {
        if (betMoney.isNegative()) {
            throw new IllegalArgumentException(NEGATIVE_BET_MONEY_EXCEPTION_MESSAGE);
        }
    }

    public void win() {
        this.profitMoney = betMoney;
    }

    public void winWithBlackjack() {
        this.profitMoney = Money.createBlackjackProfit(betMoney);
    }

    public void lose() {
        this.profitMoney = Money.createAsNegative(betMoney);
    }

    public Money getBetMoney() {
        return betMoney;
    }

    public Money getProfitMoney() {
        return profitMoney;
    }

    @Override
    public String toString() {
        return "BetAndProfit{" +
                "betMoney=" + betMoney +
                ", profit=" + profitMoney +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BetAndProfit that = (BetAndProfit) o;

        if (!betMoney.equals(that.betMoney)) {
            return false;
        }
        return profitMoney.equals(that.profitMoney);
    }

    @Override
    public int hashCode() {
        int result = betMoney.hashCode();
        result = 31 * result + profitMoney.hashCode();
        return result;
    }
}
