package blackjack.domain;

import blackjack.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    List<Player> players;

    public Players(List<String> playerNames) {
        players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
