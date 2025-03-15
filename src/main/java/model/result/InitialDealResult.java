package model.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class InitialDealResult {
    private final List<Player> winnerPlayers;
    private final List<Player> loserPlayers;

    public static InitialDealResult from(Dealer dealer, Players players) {
        if (dealer.isBlackJack()) {
            return createResultIfDealerBlackjack(players);
        }
        List<Player> blackjackPlayers = players.getPlayers().stream()
                .filter(Player::isBlackjack)
                .toList();
        return new InitialDealResult(blackjackPlayers);
    }

    private static InitialDealResult createResultIfDealerBlackjack(Players players) {
        List<Player> winnerPlayers = new ArrayList<>();
        List<Player> loserPlayers = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            if (player.isBlackjack()) {
                winnerPlayers.add(player);
                continue;
            }
            loserPlayers.add(player);
        }
        return new InitialDealResult(winnerPlayers, loserPlayers);
    }

    public InitialDealResult(List<Player> winnerPlayers) {
        this(winnerPlayers, List.of());
    }

    public InitialDealResult(List<Player> winnerPlayers, List<Player> loserPlayers) {
        this.winnerPlayers = winnerPlayers;
        this.loserPlayers = loserPlayers;
    }

    public List<Player> findWinnerPlayers() {
        return Collections.unmodifiableList(winnerPlayers);
    }

    public List<Player> findLoserPlayers() {
        return Collections.unmodifiableList(loserPlayers);
    }
}
