package domain.participant;

import domain.GameResult;
import domain.HitThreshold;
import domain.card.Card;

import java.util.Collections;
import java.util.List;

public final class Dealer extends Participant {
    private static final int FIRST_INDEX = 0;
    private static final String DEALER_NAME = "딜러";

    private ParticipantState gameState = ParticipantState.RUNNING;

    public Dealer() {
        super(HitThreshold.DEALER_THRESHOLD, DEALER_NAME);
    }

    public void stopRunning() {
        gameState = ParticipantState.END;
    }

    @Override
    public List<Card> getCards() {
        if (gameState == ParticipantState.RUNNING) {
            return Collections.singletonList(super.getCards().get(FIRST_INDEX));
        }
        return super.getCards();
    }

    @Override
    public GameResult getGameResult(final Participant player) {
        return GameResult.getDealerResult(this.cards, player.cards);
    }
}
