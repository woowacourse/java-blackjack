package blackjack.dto;

public class ProfitDto {

    private final String name;
    private final double profit;

    public ProfitDto(String name, double money) {
        this.name = name;
        this.profit = money;
    }

    public String getName() {
        return name;
    }

    public double getProfit() {
        return profit;
    }
}
