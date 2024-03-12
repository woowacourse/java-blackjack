package domain.game;

import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.score.ScoreBoard;
import domain.score.Status;

import java.util.List;
import java.util.function.Consumer;

import static domain.score.Status.LOSE;
import static domain.score.Status.WIN;

public class Referee {

    private final Rule rule = new Rule();
    private final ScoreBoard scoreBoard;

    public Referee(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void decideResult(DealerCards dealer, List<PlayerCards> players) {
        Status dealerStatus = rule.decideDealerStatus(dealer);
        checkBlackjackPlayers(players, dealerStatus);
        checkBustPlayers(players);
        checkRemainPlayers(dealer, players, dealerStatus);
    }

    private void checkBlackjackPlayers(List<PlayerCards> players, Status dealerStatus) {
        Status playerStatus = rule.decidePlayerBlackjackStatus(dealerStatus);
        players.stream()
                .filter(rule::isBlackJack)
                .forEach(updatePlayerScore(playerStatus));
    }

    private void checkBustPlayers(List<PlayerCards> players) {
        players.stream()
                .filter(rule::isBust)
                .forEach(updatePlayerScore(LOSE));
    }

    private void checkRemainPlayers(DealerCards dealer, List<PlayerCards> players, Status dealerStatus) {
        players.stream()
                .filter(player -> !rule.isBlackJack(player) && !rule.isBust(player))
                .forEach(getWinConsumer(dealerStatus, dealer));
    }

    private Consumer<PlayerCards> getWinConsumer(Status dealerStatus, DealerCards dealer) {
        if (dealerStatus == LOSE) {
            return updatePlayerScore(WIN);
        }
        return player -> compareToDealer(dealer, player);
    }

    private Consumer<PlayerCards> updatePlayerScore(Status status) {
        return player -> scoreBoard.updatePlayerScore(player.getPlayerName(), status);
    }

    private void compareToDealer(DealerCards dealer, PlayerCards player) {
        Status status = rule.decidePlayerStatus(dealer.bestSum(), player);
        scoreBoard.updatePlayerScore(player.getPlayerName(), status);
    }
}
