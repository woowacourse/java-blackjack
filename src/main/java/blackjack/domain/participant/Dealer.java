package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int STAND_BOUND = 17;
    private static final int FIRST_VISIBLE_CARD_INDEX = 1;

    public Dealer() {
        super(DEALER_NAME);
    }

    public List<Card> getVisibleCards() {
        List<Card> cards = getCards();

        return cards.subList(FIRST_VISIBLE_CARD_INDEX, cards.size());
    }

    @Override
    public boolean isPlayable() {
        int score = calculateScore();

        return score < STAND_BOUND;
    }
}
