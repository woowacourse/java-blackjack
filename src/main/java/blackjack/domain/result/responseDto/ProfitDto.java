package blackjack.domain.result.responseDto;

public class ProfitDto {
    private final String name;
    private final double profit;

    public ProfitDto(String name, double profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public double getProfit() {
        return profit;
    }
}
