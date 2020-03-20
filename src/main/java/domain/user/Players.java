package domain.user;

import domain.card.Deck;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어 리스트가 비어있습니다.");
        }
        this.players = players;
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

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}
