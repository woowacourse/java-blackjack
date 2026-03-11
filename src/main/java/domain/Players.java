package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(Deck deck, List<String> userNames) {
        List<Player> players = new ArrayList<>();
        for (String userName : userNames) {
            players.add(Player.of(deck.drawInitialHand(), userName));
        }
        this.players = players;
    }

    public static Players of(Deck deck, List<String> userNames) {
        return new Players(deck, userNames);
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
