package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(Cards cards, List<String> userNames) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < userNames.size(); i++) {
            players.add(Player.of(cards.drawInitialHand(), userNames.get(i)));
        }
        this.players = players;
    }

    public static Players of(Cards cards, List<String> userNames) {
        return new Players(cards, userNames);
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
