package model.result;

import java.util.ArrayList;
import java.util.List;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class InitialDealResult {
    private final List<Player> winnerPlayers;
    private final boolean isDealerWins;

    public static InitialDealResult from(Dealer dealer, Players players) {
        List<Player> blackjackPlayers = new ArrayList<>();
        if (dealer.isBlackJack()) {
            return new InitialDealResult(blackjackPlayers, true);
        }
        for (Player player : players.getPlayers()) {
            if (player.isBlackjack()) {
                blackjackPlayers.add(player);
            }
        }
        return new InitialDealResult(blackjackPlayers, false);
    }

    public InitialDealResult(List<Player> winnerPlayers, boolean isDealerWins) {
        this.winnerPlayers = winnerPlayers;
        this.isDealerWins = isDealerWins;
    }

    public List<Player> findWinnerPlayers() {
        return this.winnerPlayers;
    }

    public boolean isDealerWins() {
        return isDealerWins;
    }
}
