package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<String> userNames) {
        List<Player> players = new ArrayList<>();
        for (String userName : userNames) {
            players.add(Player.of(userName));
        }
        this.players = players;
    }

    public static Players of(List<String> userNames) {
        return new Players(userNames);
    }

    public List<String> getUserNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<List<Card>> getPlayersCardsInfo() {
        return players.stream()
                .map(Player::getCards)
                .toList();
    }

    public List<Integer> getPlayerScoreInfo() {
        return players.stream()
                .map(Player::calculateScore)
                .toList();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
