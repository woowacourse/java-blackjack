package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> names) {
        this.players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> players() {
        return Collections.unmodifiableList(players);
    }

    public void distributeToEachPlayer() {
        players.forEach(player -> player.distribute(Deck.popTwo()));
    }

    public List<Cards> showCardsByPlayers() {
        return players.stream()
                .map(Player::getCards)
                .collect(Collectors.toList());
    }

    public List<String> showNames() {
        return Collections.unmodifiableList(players.stream()
                .map(Player::getName)
                .collect(Collectors.toList()));
    }
}
