package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;

public class Challengers {
    private final List<Challenger> challengers;

    public Challengers(final List<Challenger> challengers) {
        validateChallengersCount(challengers);
        this.challengers = new ArrayList<>(challengers);
    }

    public List<Challenger> toList() {
        return new ArrayList<>(challengers);
    }

    private void validateChallengersCount(final List<Challenger> challengers) {
        if (challengers.size() > 8) {
            throw new IllegalArgumentException("게임 참가자의 수는 8명을 넘을 수 없습니다.");
        }
    }
}
