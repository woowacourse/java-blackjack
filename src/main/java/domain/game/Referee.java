package domain.game;

import domain.card.Cards;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.score.Outcome;
import domain.score.ScoreBoard;

import java.util.List;

import static domain.score.Outcome.*;

public class Referee {

    private static final int STANDARD = 21;

    private final ScoreBoard scoreBoard;

    public Referee(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void decideResult(DealerCards dealer, List<PlayerCards> players) {
        for (PlayerCards player : players) {
            Outcome outcome = judgeOutcome(dealer, player);
            scoreBoard.updatePlayerScore(player.getPlayerName(), outcome);
        }
    }

    private Outcome judgeOutcome(DealerCards dealer, PlayerCards player) {
        if (isBust(player)) {
            return LOSE;
        }
        if (isBlackJack(player)) {
            return decidePlayerBlackjack(dealer);
        }
        if (isBust(dealer)) {
            return WIN;
        }
        return compare(dealer, player);
    }

    private Outcome decidePlayerBlackjack(DealerCards dealer) {
        if (isBlackJack(dealer)) {
            return TIE;
        }
        return BLACKJACK;
    }

    private Outcome compare(DealerCards dealer, PlayerCards playerCards) {
        int dealerSum = dealer.bestSum();
        int playerSum = playerCards.bestSum();

        if (dealerSum < playerSum) {
            return Outcome.WIN;
        }
        if (dealerSum > playerSum) {
            return Outcome.LOSE;
        }
        return Outcome.TIE;
    }

    private boolean isBlackJack(Cards cards) {
        return cards.sumInitCards() == STANDARD;
    }

    private boolean isBust(Cards cards) {
        return cards.bestSum() > STANDARD;
    }
}
