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
    private List<PlayerResult> results;

    public ScoreBoard(final Participant dealer, final Players players){
        this.dealerScore = dealer.sumCardsForResult();
        this.results = playerResults(players);
    }

    public List<PlayerResult> getUnmodifiableResults(){
        return Collections.unmodifiableList(results);
    }

    private List<PlayerResult> playerResults(final Players players) {
        List<PlayerResult> results = new ArrayList<>();
        for (Player player: players.getUnmodifiableList()){
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
        if(playerScore.isBlackjack()){
            return Outcome.DRAW;
        }
        return Outcome.LOSE;
    }

    private Outcome burstCase() {
        return Outcome.WIN;
    }

    private Outcome compareWith(Score playerScore) {
        return getInstance(playerScore, dealerScore);
    }

    private Outcome getInstance(final Score base, final Score counterpart) {
        if (draw(base, counterpart)) {
            return Outcome.DRAW;
        }

        if (win(base, counterpart)) {
            return Outcome.WIN;
        }

        return Outcome.LOSE;
    }

    private boolean draw(final Score base, final Score counterpart) {
        if (base.isBurst() && counterpart.isBurst()) {
            return true;
        }
        return base.isNotBurst() && counterpart.isNotBurst() && base.isSameAs(counterpart);
    }

    private boolean win(final Score base, final Score counterpart) {
        if (base.isBlackjack() && counterpart.isNotBlackjack()) {
            return true;
        }
        if (base.isNotBurst() && counterpart.isBurst()) {
            return true;
        }
        return base.isNotBurst() && counterpart.isNotBurst() && base.isHigherThan(counterpart);
    }
}
