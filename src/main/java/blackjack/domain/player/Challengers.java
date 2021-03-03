package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;

public class Challengers {
    private final List<Challenger> challengers;

    public Challengers(final List<Challenger> challengers) {
        this.challengers = new ArrayList<>(challengers);
    }

    public List<Challenger> getPlayers() {
        return new ArrayList<>(challengers);
    }
}
