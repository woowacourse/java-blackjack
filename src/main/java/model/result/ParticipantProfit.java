package model.result;

public class ParticipantProfit {

    private final String name;
    private final int profit;

    public ParticipantProfit(String name, int profit) {
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
