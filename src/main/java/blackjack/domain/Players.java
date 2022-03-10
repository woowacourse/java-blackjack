package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Players {
    private List<Player> players;

    public Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private Player convertToPlayer(String name) {
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

    public List<Player> getPlayers() {
        return players;
    }
}
