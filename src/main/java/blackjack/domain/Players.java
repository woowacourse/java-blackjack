package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
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
        for (String name : cardForPlayers.keySet()) {
            convertToPlayer(name).addCard(cardForPlayers.get(name));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
