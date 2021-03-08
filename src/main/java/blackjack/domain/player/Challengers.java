package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;

public class Challengers {

    private final List<Challenger> challengers;

    public Challengers(final List<Challenger> challengers) {
        validateChallengersCount(challengers);
        this.challengers = new ArrayList<>(challengers);
    }

    public List<Challenger> getChallengersAsList() {
        return new ArrayList<>(challengers);
    }

    private void validateChallengersCount(final List<Challenger> challengers) {
        if (challengers.size() < 1 || challengers.size() > 7) {
            throw new IllegalArgumentException("게임 참가자의수는 1명 이상 7명 이하여야 합니다.");
        }
    }
}
