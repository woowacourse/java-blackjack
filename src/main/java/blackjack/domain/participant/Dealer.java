package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Score;
import java.util.List;

public final class Dealer extends Participant {

    private static final int DEALER_MIN_RECEIVE_CARD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(Hand.generateEmptyCards(), DEALER_NAME);
    }

    @Override
    public List<Card> showInitCards() {
        return List.of(getHand().get(0));
    }

    @Override
    public boolean canDraw() {
        Score score = calculateTotalScore();
        return score.canDraw(new Score(DEALER_MIN_RECEIVE_CARD));
    }
}
