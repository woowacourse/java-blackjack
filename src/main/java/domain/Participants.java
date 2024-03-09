package domain;

import domain.constants.Outcome;
import java.util.List;
import java.util.function.Function;

public class Participants {
    private final List<Player> players;

    public Participants(final List<Player> players) {
        this.players = players;
    }

    public List<Outcome> getOutcomesIf(final Function<Player, Boolean> function) {
        return players.stream()
                .map(player -> Outcome.from(function.apply(player)))
                .toList();
    }

    public Player getDealer() {
        return players.stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
