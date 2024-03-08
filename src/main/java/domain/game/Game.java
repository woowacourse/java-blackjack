package domain.game;

import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.score.ScoreBoard;
import domain.score.Status;

import java.util.List;

public class Game {

    private final Rule rule;
    private final ScoreBoard scoreBoard;

    public Game(Rule rule, ScoreBoard scoreBoard) {
        this.rule = rule;
        this.scoreBoard = scoreBoard;
    }

    public void decideResult(DealerCards dealer, List<PlayerCards> players) {
        players.forEach(player -> decideResult(dealer, player));
    }

    private void decideResult(DealerCards dealer, PlayerCards player) {
        Status dealerStatus = rule.decideStatus(dealer, player);
        Status playerStatus = rule.decideStatus(player, dealer);
        scoreBoard.updateDealerScore(dealerStatus);
        scoreBoard.updatePlayerScore(player.getPlayerName(), playerStatus);
    }
}
