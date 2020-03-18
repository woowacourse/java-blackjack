package domain.user;

import domain.Names;
import domain.card.CardDeck;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(Names names) {
        players = names.get()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> get() {
        return Collections.unmodifiableList(players);
    }

    public void firstDraw(CardDeck cardDeck) {
        for (Player player : players) {
            player.firstDraw(cardDeck);
        }
    }

    public List<String> getStatus() {
        return players.stream()
                .map(Player::getStatus)
                .collect(Collectors.toList());
    }

    public List<String> getName() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
