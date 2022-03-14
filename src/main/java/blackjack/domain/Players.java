package blackjack.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Players {
    private static final int FIRST_PLAYER_INDEX = 0;
    private static final int NEXT_GAP = 1;
    private List<Player> players;

    public Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public Player convertToPlayer(String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findFirst()
                .orElseThrow();
    }

    public void addCardToPlayers(Map<String, Card> cardForPlayers) {
        for (Entry<String, Card> entry : cardForPlayers.entrySet()) {
            convertToPlayer(entry.getKey()).addCard(entry.getValue());
        }
    }

    public List<String> namesAbleToGetAdditionalCard() {
        return players.stream()
                .filter(player -> !player.isBurst())
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> playersAbleToGetAdditionalCard() {
        return players.stream()
                .filter(player -> !player.isBurst())
                .collect(Collectors.toList());
    }

    public boolean isPlayerBurst(String playerName) {
        return convertToPlayer(playerName).isBurst();
    }

    public Player firstPlayer() {
        return players.get(FIRST_PLAYER_INDEX);
    }

    public boolean hasNextPlayer(Player player) {
        return !player.equals(players.get(players.size() - 1));
    }

    public Player nextPlayer(Player player) {
        if (!hasNextPlayer(player)) {
            return null;
        }
        return players.get(players.indexOf(player) + NEXT_GAP);
    }
}
