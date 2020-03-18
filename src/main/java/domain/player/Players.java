package domain.player;

import domain.card.CardDeck;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> users;

    public Players(CardDeck cardDeck, Map<String, Double> playerInformation) {
        this.users = playerInformation.entrySet()
                .stream()
                .map(entry -> new Player(entry.getKey(), cardDeck.giveTwoCardStartGame(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(users);
    }

}
