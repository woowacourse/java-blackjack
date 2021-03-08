package blackjack.domain.participant;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Players {
    private final List<Player> players;

    public Players(List<String> playerNames) {
        players = playerNames.stream()
                .map(Player::new).collect(toList());
    }

    public void addCardToPlayer() {
        for (Player player : players) {
            player.addCard();
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<String, String> getGameResults(final Dealer dealer) {
        return players.stream()
                .collect(toMap(Player::getName, player -> GameResult.getWinLoseResult(player, dealer)));
    }
}
