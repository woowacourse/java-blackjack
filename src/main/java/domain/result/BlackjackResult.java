package domain.result;

import domain.participant.dealer.Dealer;
import domain.participant.dealer.DealerResult;
import domain.participant.player.PlayerResults;
import domain.participant.player.Players;

public class BlackjackResult {

    private final DealerResult dealerResult;
    private final PlayerResults playerResults;

    private BlackjackResult(final DealerResult dealerResult, final PlayerResults playerResults) {
        this.dealerResult = dealerResult;
        this.playerResults = playerResults;
    }

    public static BlackjackResult of(final Dealer dealer, final Players players) {
        DealerResult dealerResult = new DealerResult(dealer);
        PlayerResults playerResults = new PlayerResults();
        players.forEach(player -> {
            Profit playerProfit = player.profitAgainst(dealer);
            dealerResult.subtract(playerProfit);
            playerResults.put(player, playerProfit);
        });
        return new BlackjackResult(dealerResult, playerResults);
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public PlayerResults getPlayerResults() {
        return playerResults;
    }
}
