package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> names) {
        return new Players(names.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public List<Player> players() {
        return Collections.unmodifiableList(players);
    }

    public void distributeToEachUser() {
        players.forEach(player -> player.receiveCards(Deck.popTwo()));
    }

    public List<Cards> showCardsByUsers() {
        return players.stream()
                .map(Player::showCards)
                .collect(Collectors.toList());
    }
}
