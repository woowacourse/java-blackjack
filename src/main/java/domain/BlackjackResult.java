package domain;

import java.util.EnumMap;

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
        DealerResult dealerResult = new DealerResult(dealer, new EnumMap<>(BlackjackResultStatus.class));
        PlayerResults playerResults = new PlayerResults();
        players.forEach(player -> {
            dealerResult.put(dealer.resultStatusAgainst(player));
            playerResults.put(player, player.resultStatusAgainst(dealer));
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
