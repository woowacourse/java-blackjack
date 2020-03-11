package domain.user;

import domain.card.Card;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import utils.CustomCollector;

public class Players {

    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playerNames) {
        if (playerNames == null || playerNames.isEmpty()) {
            throw new NullPointerException("이름이 비어있습니다.");
        }
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
    }

    public void receiveFirstCards(Deck deck) {
        for (Player player : players) {
            player.receiveFirstCards(deck);
        }
    }

    public List<String> getNames() {
        return players.stream().
            map(Player::getName)
            .collect(Collectors.toList());
    }

    public List<Card> getCardsByName(String name) {
        return players.stream()
            .filter(player -> player.isSameName(name))
            .collect(CustomCollector.toSingleElement())
            .getCards();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}
