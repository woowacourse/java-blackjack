package domain.participant;

import domain.GameResult;
import domain.HitThreshold;
import domain.card.Card;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Dealer extends Participant {
    private static final int FIRST_INDEX = 0;

    private ParticipantState gameState = ParticipantState.RUNNING;

    public Dealer() {
        super(HitThreshold.DEALER_THRESHOLD, "딜러");
    }

    public void stopRunning() {
        gameState = ParticipantState.END;
    }

    public Map<String, List<GameResult>> getGameResultWithName(final List<GameResult> playersResult) {
        return new LinkedHashMap<>(Map.of(name, GameResult.reverse(playersResult)));
    }

    @Override
    public List<Card> getCards() {
        if (gameState == ParticipantState.RUNNING) {
            return Collections.singletonList(cards.getCards().get(FIRST_INDEX));
        }
        return cards.getCards();
    }
}
