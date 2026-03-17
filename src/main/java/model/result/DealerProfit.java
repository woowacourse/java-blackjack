package model.result;

public class DealerProfit {
    private Long profit = 0L;

    public void increase(Long betAmount) {
        profit += betAmount;
    }

    public ParticipantProfit getDealerProfit(String dealerName) {
        return new ParticipantProfit(dealerName, profit);
    }
}
