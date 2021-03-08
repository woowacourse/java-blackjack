package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.*;

public class Players {
    private final List<Player> players;

    public Players(List<String> names) {
        this.players = names.stream()
                .map(Player::new)
                .collect(toList());
    }

    public void distributeToEachPlayer(Deck deck) {
        this.players.forEach(player -> player.distribute(deck.popTwo()));
    }

    public List<Cards> showCardsByPlayers() {
        return this.players.stream()
                .map(Player::getCards)
                .collect(toList());
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(this.players.stream()
                .map(Player::getName)
                .collect(toList()));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}
