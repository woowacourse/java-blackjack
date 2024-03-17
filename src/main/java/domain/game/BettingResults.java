package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public record BettingResults(int dealerRevenue, Map<PlayerName, Integer> playerRevenues) {

    public static BettingResults of(final BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        List<Player> players = blackjackGame.getPlayers();

        Map<PlayerName, Integer> playerRevenues = parsePlayersRevenues(dealer, players);
        int dealerRevenue = parseDealerRevenue(dealer, playerRevenues);

        return new BettingResults(dealerRevenue, playerRevenues);
    }

    private static Map<PlayerName, Integer> parsePlayersRevenues(final Dealer dealer, final List<Player> players) {
        return players
                .stream()
                .collect(toMap(Player::getPlayerName, player -> player.calculateRevenue(dealer)));
    }

    private static int parseDealerRevenue(final Dealer dealer, final Map<PlayerName, Integer> playerRevenues) {
        return dealer.calculateRevenue(
                playerRevenues.values()
                        .stream()
                        .toList()
        );
    }
}
