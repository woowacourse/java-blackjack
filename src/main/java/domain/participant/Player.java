package domain.participant;

import domain.HitThreshold;
import domain.GameResult;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Player extends Participant {

    public Player(final String name) {
        super(HitThreshold.PLAYER_THRESHOLD, name);
    }

    public String getName() {
        return name;
    }

    public Map<String, GameResult> getGameResultWithName(final Participant other) {
        return new LinkedHashMap<>(Map.of(name, cards.calculateGameResult(other.cards)));
    }
}
