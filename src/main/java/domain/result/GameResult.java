package domain.result;

import domain.player.Dealer;
import domain.player.Players;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class GameResult {

    private final Dealer dealer;
    private final Players players;

    public GameResult(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public EnumMap<Outcome, Integer> makeDealerResult() {
        EnumMap<Outcome, Integer> dealerResult = initDealerResult();
        Map<String, Outcome> playerResults = makePlayerResults();

        playerResults.values()
                .forEach(outcome -> {
                    Outcome dealerOutcome = Outcome.reverseOutcome(outcome);
                    dealerResult.put(dealerOutcome, dealerResult.get(dealerOutcome) + 1);
                });
        return dealerResult;
    }

    private EnumMap<Outcome, Integer> initDealerResult() {
        EnumMap<Outcome, Integer> dealerResult = new EnumMap<>(Outcome.class);

        for (Outcome outcome : Outcome.values()) {
            dealerResult.put(outcome, 0);
        }
        return dealerResult;
    }

    public Map<String, Outcome> makePlayerResults() {
        Map<String, Outcome> playerResults = new LinkedHashMap<>();
        players.getPlayers()
                .forEach((player ->
                        playerResults.put(
                                player.getName(),
                                Outcome.decideOutcome(player.getScore(), dealer.getScore())
                        )
                ));
        return playerResults;
    }
}
