package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(final String input) {
        this.players = makePlayers(input);
    }

    private List<Player> makePlayers(final String input) {
        return Arrays.stream(input.split(","))
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}
