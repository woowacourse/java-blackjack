package domain.result;

public enum Result {
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1);

    private static final int NOT_WIN_BOUNDARY = 0;
    private static final double BLACKJACK_WIN_MONEY_RATE = 1.5;
    private final String korean;
    private final int additionalMoneyRate;

    Result(final String resultKorean, final int moneyResult) {
        this.korean = resultKorean;
        this.additionalMoneyRate = moneyResult;
    }

    public double calculateResultMoney(double money, boolean isBlackJack) {
        double resultMoney = money * this.additionalMoneyRate;
        if (resultMoney > NOT_WIN_BOUNDARY && isBlackJack) {
            resultMoney *= BLACKJACK_WIN_MONEY_RATE;
        }
        return resultMoney;
    }

    @Override
    public String toString() {
        return this.korean;
    }
}
