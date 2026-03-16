package domain.model;

import java.util.*;

public class Players {

    private final Map<String, Player> players = new LinkedHashMap<>();

    public Player save(Player player) {
        players.put(player.getName(), player);
        return player;
    }

    public List<Player> saveAllPlayers(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::of)
                .toList();
        players.forEach(player -> this.players.put(player.getName(), player));
        return players;
    }

    public List<Player> getAllPlayers() {
        return players.values().stream().toList();
    }

    public void giveDeck(Player player, Deck deck) {
        player.assignDeck(deck);
    }
}
