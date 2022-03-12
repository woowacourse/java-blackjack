package domain.participant;

import domain.CardScoreThreshold;
import domain.GameState;
import domain.card.Card;
import java.util.Collections;
import java.util.List;

public final class Dealer extends Participant {
    private static final int FIRST_INDEX = 0;

    private GameState gameState = GameState.RUNNING;

    public Dealer() {
        super(CardScoreThreshold.DEALER_THRESHOLD, "딜러");
    }

//    @Override
//    public List<FinalGameResult> calculateFinalGameResult(Participant other) {
//        return null;
//    }

    @Override
    public List<Card> getCards() {
        if (gameState == GameState.RUNNING) {
            return Collections.singletonList(cards.getCards().get(FIRST_INDEX));
        }
        return cards.getCards();
    }

    public void stopRunning() {
        gameState = GameState.END;
    }
}
