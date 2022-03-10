package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<String> playerNames) {
        this.players = new ArrayList<>(playerNames.stream()
                .map(Player::of)
                .collect(Collectors.toList()));
    }
}
