package blackjack.dto;

public class ProfitResultDto {
    private String name;
    private int profit;

    public ProfitResultDto(String name, int profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
