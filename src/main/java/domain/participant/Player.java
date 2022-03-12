package domain.participant;

import domain.CardScoreThreshold;
import domain.GameResult;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Player extends Participant {

    public Player(final String name) {
        super(CardScoreThreshold.PLAYER_THRESHOLD, name);
    }

    public String getName() {
        return name;
    }

    public Map<String, GameResult> getGameResultWithName(Participant other) {
        return new LinkedHashMap<>(Map.of(name, cards.calculateGameResult(other.cards)));
    }
}
