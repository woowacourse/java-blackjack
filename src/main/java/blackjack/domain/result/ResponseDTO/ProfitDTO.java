package blackjack.domain.result.ResponseDTO;

public class ProfitDTO {
    private final String name;
    private final double profit;

    public ProfitDTO(String name, double profit) {
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
