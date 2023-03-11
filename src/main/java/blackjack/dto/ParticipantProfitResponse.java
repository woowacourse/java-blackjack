package blackjack.dto;

public class ParticipantProfitResponse {
    private final String name;
    private final int profit;

    public ParticipantProfitResponse(String name, int profit) {
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
