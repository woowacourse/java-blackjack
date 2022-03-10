package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames) {
        return new Players(new ArrayList<>(playerNames.stream()
                        .map(Player::of)
                        .collect(Collectors.toList())));
    }

    public void receive(CardDeck cardDeck) {
        for (Player player : players) {
            player.receive(cardDeck.distribute(2));
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
