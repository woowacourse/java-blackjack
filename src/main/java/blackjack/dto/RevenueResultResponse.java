package blackjack.dto;

import blackjack.RevenueResult;
import blackjack.domain.participant.Dealer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RevenueResultResponse {

    private String dealerRevenueMessage;
    private List<String> playersRevenueMessage = new ArrayList<>();

    private RevenueResultResponse(RevenueResult revenueResult) {
        makeDealerRevenueMessage(revenueResult);
        makePlayersRevenueMessage(revenueResult);
    }

    public static RevenueResultResponse from(RevenueResult revenueResult) {
        return new RevenueResultResponse(revenueResult);
    }

    private void makeDealerRevenueMessage(RevenueResult revenueResult) {
        String dealerName = Dealer.DEALER_DEFAULT_NAME;
        int dealerEarnings = revenueResult.getDealerEarnings();
        dealerRevenueMessage = String.format("%s: %d", dealerName, dealerEarnings);
    }

    private void makePlayersRevenueMessage(RevenueResult revenueResult) {
        Map<String, Integer> playersEarnings = revenueResult.getPlayersEarnings();
        for (Entry<String, Integer> entry : playersEarnings.entrySet()) {
            String playerName = entry.getKey();
            Integer playerEarnings = entry.getValue();
            String playerRevenueMessage = String.format("%s: %d", playerName, playerEarnings);
            playersRevenueMessage.add(playerRevenueMessage);
        }
    }

    public String getDealerRevenueMessage() {
        return dealerRevenueMessage;
    }

    public List<String> getPlayersRevenueMessage() {
        return playersRevenueMessage;
    }
}
