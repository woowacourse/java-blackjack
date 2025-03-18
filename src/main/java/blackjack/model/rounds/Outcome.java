package blackjack.model.rounds;

import blackjack.model.bettings.Result;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.ParticipantType;
import blackjack.model.participants.Player;
import blackjack.model.participants.Players;
import java.util.HashMap;
import java.util.Map;

public class Outcome {
    private final Map<Player, Result> dealerOutcome;
    private final Map<Player, Result> playersOutcome;

    public Outcome() {
        this.dealerOutcome = new HashMap<>();
        this.playersOutcome = new HashMap<>();
    }

    public void checkFinalOutcome(Dealer dealer, Players players) {
        evaluateDealerResult(dealer, players);
        evaluatePlayersResult(dealer, players);

        updateDealerWager(dealer);
        updatePlayersWager();
    }

    private void evaluateDealerResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            Result result = Result.evaluateResult(ParticipantType.DEALER, dealer.getHand(), player.getHand());
            dealerOutcome.put(player, result);
        }
    }

    private void evaluatePlayersResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            Result result = Result.evaluateResult(ParticipantType.PLAYER, player.getHand(), dealer.getHand());
            playersOutcome.put(player, result);
        }
    }

    private void updateDealerWager(Dealer dealer) {
        for (Map.Entry<Player, Result> entry : dealerOutcome.entrySet()) {
            Player player = entry.getKey();
            Result result = entry.getValue();
            dealer.updateWager(result.getRate(), player.getWager());
        }
    }

    private void updatePlayersWager() {
        for (Map.Entry<Player, Result> entry : playersOutcome.entrySet()) {
            Player player = entry.getKey();
            Result result = entry.getValue();
            player.updateWager(result.getRate());
        }
    }
}
