package domain.participant;

import domain.HitThreshold;
import domain.GameResult;
import domain.GameState;
import domain.card.Card;
import domain.card.CardDeck;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Dealer extends Participant {
    private static final int FIRST_INDEX = 0;

    private GameState gameState = GameState.RUNNING;

    public Dealer() {
        super(HitThreshold.DEALER_THRESHOLD, "딜러");
    }

    public void stopRunning() {
        gameState = GameState.END;
    }

    public Map<String, List<GameResult>> getGameResultWithName(final List<GameResult> playersResult) {
        return new LinkedHashMap<>(Map.of(name, GameResult.reverse(playersResult)));
    }

    public boolean hit() {
        if (canReceiveCard()) {
            receiveCard(CardDeck.draw());
            return true;
        }
        return false;
    }

    @Override
    public List<Card> getCards() {
        if (gameState == GameState.RUNNING) {
            return Collections.singletonList(cards.getCards().get(FIRST_INDEX));
        }
        return cards.getCards();
    }
}
