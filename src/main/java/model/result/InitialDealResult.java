package model.result;

import java.util.ArrayList;
import java.util.List;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class InitialDealResult {
    private final List<Player> winnerPlayers;

    public static InitialDealResult from(Dealer dealer, Players players) {
        List<Player> blackjackPlayers = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            if (player.isBlackjack()) {
                blackjackPlayers.add(player);
            }
        }
        return new InitialDealResult(blackjackPlayers);
    }

    public InitialDealResult(List<Player> winnerPlayers) {
        this.winnerPlayers = winnerPlayers;
    }

    public List<Player> findWinnerPlayers() {
        return this.winnerPlayers;
    }
}
