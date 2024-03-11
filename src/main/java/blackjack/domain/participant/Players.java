package blackjack.domain.participant;

import blackjack.domain.card.HandGenerator;
import blackjack.exception.InvalidPlayerCountException;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Name> playerNames, HandGenerator handGenerator) {
        checkPlayersEmpty(playerNames);
        this.players = playerNames.stream()
                .map(playerName -> new Player(playerName, handGenerator))
                .toList();
    }

    private void checkPlayersEmpty(List<Name> playerNames) {
        if (playerNames.isEmpty()) {
            throw new InvalidPlayerCountException();
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
