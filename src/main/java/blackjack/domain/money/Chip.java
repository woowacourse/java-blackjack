package blackjack.domain.money;

public class Chip {
    private final int betting;
    private Long profit;

    public Chip(int betting) {
        validateNaturalNumber(betting);
        this.betting = betting;
        this.profit = 0L;
    }

    private void validateNaturalNumber(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("0 이상의 정수를 입력해 주세요.");
        }
    }

    public void addProfit(Long addition) {
        profit += addition;
    }

    public int betting() {
        return betting;
    }

    public Long profit() {
        return profit;
    }
}
