package blackjack.domain.participant;

import blackjack.domain.HandGenerator;
import blackjack.exception.InvalidPlayerCountException;

import java.util.List;

public class Players {
    private final List<Player> values;

    public Players(List<Name> playerNames, HandGenerator handGenerator) {
        if (playerNames.isEmpty()) {
            throw new InvalidPlayerCountException();
        }
        this.values = playerNames.stream()
                .map(playerName -> new Player(playerName, handGenerator))
                .toList();
    }

    public List<Player> getValues() {
        return values;
    }
}
