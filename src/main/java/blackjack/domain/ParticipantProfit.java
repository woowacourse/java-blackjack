package blackjack.domain;

public class ParticipantProfit {

    private final String name;
    private final Profit profit;

    public ParticipantProfit(String name, Profit profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public Profit getProfit() {
        return profit;
    }
}
