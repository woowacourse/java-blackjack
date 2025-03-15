package domain.participant;

import domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final int DEALER_SCORE_THRESHOLD = 16;

    protected Dealer() {
        super("딜러");
    }

    @Override
    public List<Card> getInitialCards() {
        List<Card> cards = super.getCards();
        return cards.subList(0, 1);
    }

    @Override
    public boolean ableToAddCard() {
        int cardsScore = cards.calculateScore();
        return cardsScore <= DEALER_SCORE_THRESHOLD;
    }
}
