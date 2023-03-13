package blackjack.dto;

public class PlayerProfitDto {

    private final String name;
    private final int profit;

    public PlayerProfitDto(final String name, final int profit) {
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
