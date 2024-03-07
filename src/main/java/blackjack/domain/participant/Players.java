package blackjack.domain.participant;

import blackjack.dto.GameResult;
import blackjack.dto.PlayerResult;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> names, final Dealer dealer) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, dealer))
                .toList();

        return new Players(players);
    }

    public GameResult createResult(final Dealer dealer) {
        int wins = 0;
        int loses = 0;

        long dealerScore = dealer.hand.calculateScore();
        PlayerResult playerResult = new PlayerResult();

        for (Player player : players) {
            if (player.isBurst()) {
                wins += 1;
                playerResult.addResult(player, false);
                continue;
            }
            if (dealerScore <= 21) {
                if (dealerScore >= player.hand.calculateScore()) {
                    wins += 1;
                    playerResult.addResult(player, false);
                    continue;
                }
                loses += 1;
                playerResult.addResult(player, true);
            }

            playerResult.addResult(player, true);
        }
        return GameResult.from(wins, loses, playerResult);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
