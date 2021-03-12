package blackjack.domain.gametable;

import blackjack.domain.card.Score;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoard {
    private final Score dealerScore;
    private final List<PlayerResult> results;

    public ScoreBoard(final Participant dealer, final Players players) {
        this.dealerScore = dealer.sumCardsForResult();
        this.results = playerResults(players);
    }

    public List<PlayerResult> getUnmodifiableResults() {
        return Collections.unmodifiableList(results);
    }

    private List<PlayerResult> playerResults(final Players players) {
        List<PlayerResult> results = new ArrayList<>();
        for (Player player : players.getUnmodifiableList()) {
            results.add(new PlayerResult(player, outcomeOfPlayer(player)));
        }
        return results;
    }

    private Outcome outcomeOfPlayer(final Player player) {
        final Score playerScore = player.sumCardsForResult();
        if (dealerScore.isBlackjack()) {
            return blackjackCase(playerScore);
        }
        if (dealerScore.isBurst()) {
            return burstCase();
        }
        return compareWith(playerScore);
    }

    private Outcome blackjackCase(Score playerScore) {
        if (playerScore.isBlackjack()) {
            return Outcome.DRAW;
        }
        return Outcome.LOSE;
    }

    private Outcome burstCase() {
        return Outcome.WIN;
    }

    private Outcome compareWith(Score playerScore) {
        return Outcome.getInstance(playerScore, dealerScore);
    }

}
