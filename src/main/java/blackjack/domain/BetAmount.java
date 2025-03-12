package blackjack.domain;

public record BetAmount(int amount) {
    public BetAmount {
        validateAmount(amount);
    }

    private void validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수일 수 없습니다.");
        }
    }

    public Profit getProfit(GameResult gameResult) {
        if (gameResult == GameResult.LOSE) {
            return new Profit(negate());
        }
        if (gameResult == GameResult.BLACKJACK) {
            return new Profit(amount * 1.5);
        }
        if (gameResult == GameResult.WIN) {
            return new Profit(amount);
        }
        return new Profit(0);
    }

    public int negate() {
        return amount() * -1;
    }
}
