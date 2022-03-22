package blackjack.dto;

import blackjack.domain.BettingReturn;
import blackjack.domain.participant.Dealer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BettingReturnResponse {

    private String dealerRevenueMessage;
    private List<String> playersRevenueMessage = new ArrayList<>();

    private BettingReturnResponse(BettingReturn bettingReturn) {
        makeDealerRevenueMessage(bettingReturn);
        makePlayersRevenueMessage(bettingReturn);
    }

    public static BettingReturnResponse from(BettingReturn bettingReturn) {
        return new BettingReturnResponse(bettingReturn);
    }

    private void makeDealerRevenueMessage(BettingReturn bettingReturn) {
        String dealerName = Dealer.DEALER_DEFAULT_NAME;
        int dealerEarnings = bettingReturn.getDealerReturn();
        dealerRevenueMessage = String.format("%s: %d", dealerName, dealerEarnings);
    }

    private void makePlayersRevenueMessage(BettingReturn bettingReturn) {
        Map<String, Integer> playersEarnings = bettingReturn.getPlayersReturn();
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
