package blackjack.domain.participant;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> playerNames) {
        players = playerNames.stream()
                .map(Player::new).collect(Collectors.toList());
    }

    public void addCardToPlayer() {
        for (Player player : players) {
            player.addCard();
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
