package blackjack.domain.bet;

public class Bet {

    private final int UNIT_AMOUNT = 100;
    private final int MAX_AMOUNT = 1000000;

    private final int amount;

    public Bet(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 1 이상의 양수여야 합니다.");
        }
        if (amount % UNIT_AMOUNT != 0) {
            throw new IllegalArgumentException(String.format("배팅 금액은 %d 단위여야 합니다.", UNIT_AMOUNT));
        }
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("배팅 금액은 %d 이하여야 합니다.%n(도박문제 전문상담 국번없이 1336)", MAX_AMOUNT));
        }
    }

    public int calculateProfit(ProfitRate profitRate) {
        return profitRate.getProfit(amount);
    }
}
