package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(Deck deck, List<String> userNames) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < userNames.size(); i++) {
            players.add(Player.of(deck.drawInitialHand(), userNames.get(i)));
        }
        this.players = players;
    }

    public static Players of(Deck deck, List<String> userNames) {
        return new Players(deck, userNames);
    }

    public List<String> getUserNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<String> getPlayerInfo() {
        return players.stream()
                .map(Player::getPlayerInfo)
                .toList();
    }

    public List<String> getPlayerScoreInfo() {
        return players.stream()
                .map(Player::getPlayerScoreResult)
                .toList();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
