package blackjackgame.domain.user.dto;

public class ProfitDto {
    private final int profit;

    public ProfitDto(int profit) {
        this.profit = profit;
    }

    public int getProfit() {
        return profit;
    }
}
