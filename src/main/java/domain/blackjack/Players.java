package domain.blackjack;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(playerName -> Player.from(playerName, HoldingCards.of()))
                .toList();
    }

    public Map<String, GameResult> calculateGameResultsWithAsMap(Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(Player::getRawName, player -> player.calculateGameResult(dealer)));
    }

    List<GameResult> calculateGameResultsWith(Dealer dealer) {
        return players.stream()
                .map(player -> player.calculateGameResult(dealer))
                .toList();
    }

    public void forEach(Consumer<Player> consumer) {
        players.forEach(consumer);
    }

    public List<String> getPlayerNames() {
        return players.stream().map(Player::getRawName).toList();
    }
}
