package domain.game;

import domain.card.Cards;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.score.ScoreBoard;
import domain.score.Status;

import java.util.List;

public class Rule {

    private static final int STANDARD = 21;

    private final ScoreBoard scoreBoard;

    public Rule(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public Status decideStatus(Cards targetCards, Cards otherCards) {
        if (targetCards.bestSum() > STANDARD) {
            return Status.LOSE;
        }

        if (otherCards.bestSum() > STANDARD) {
            return Status.WIN;
        }

        if (otherCards.bestSum() < targetCards.bestSum()) {
            return Status.WIN;
        }

        if (otherCards.bestSum() > targetCards.bestSum()) {
            return Status.LOSE;
        }
        return Status.TIE;
    }

    public void decideResult(DealerCards dealer, List<PlayerCards> players) {
        players.forEach(player -> decideResult(dealer, player));
    }

    private void decideResult(DealerCards dealer, PlayerCards player) {
        Status dealerStatus = decideStatus(dealer, player);
        Status playerStatus = decideStatus(player, dealer);
        scoreBoard.updateDealerScore(dealerStatus);
        scoreBoard.updatePlayerScore(player.getPlayerName(), playerStatus);
    }
}
