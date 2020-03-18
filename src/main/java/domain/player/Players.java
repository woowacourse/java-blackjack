package domain.player;

import domain.card.CardDeck;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> users;

    public Players(CardDeck cardDeck, List<String> playerNames) {
        this.users = playerNames.stream()
                .map(name -> new Player(name, cardDeck.giveTwoCardStartGame()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(users);
    }

}
