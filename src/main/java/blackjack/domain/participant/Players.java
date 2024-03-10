package blackjack.domain.participant;

import blackjack.domain.card.HandGenerator;
import blackjack.exception.InvalidPlayerCountException;
import java.util.List;

public class Players {
    private final List<Player> values;

    public Players(List<Name> playerNames, HandGenerator handGenerator) {
        checkPlayersEmpty(playerNames);
        this.values = playerNames.stream()
                .map(playerName -> new Player(playerName, handGenerator))
                .toList();
    }

    private void checkPlayersEmpty(List<Name> playerNames) {
        if (playerNames.isEmpty()) {
            throw new InvalidPlayerCountException();
        }
    }

    public List<Player> getValues() {
        return values;
    }
}
