package model.result;

import dto.result.ParticipantProfit;

public class DealerProfit {
    private Integer profit = 0;

    public void increase(Integer betAmount) {
        profit += betAmount;
    }

    public ParticipantProfit getDealerProfit(String dealerName) {
        return new ParticipantProfit(dealerName, profit);
    }
}
