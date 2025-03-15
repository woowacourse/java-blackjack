package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {
    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer(final Hand hand) {
        super(hand);
    }

    public void draw(final Card card) {
        if (isUnderThreshold()) {
            addCard(card);
        }
    }

    public boolean isUnderThreshold() {
        return calculateSum() <= DEALER_DRAW_THRESHOLD;
    }

    @Override
    public void addCard(final Card card) {
        super.addCard(card);
    }

    @Override
    public int calculateSum() {
        return super.calculateSum();
    }

    @Override
    public List<Card> openInitialCards() {
        return List.of(getCards().getHand().getFirst());
    }

    public Hand getCards() {
        return super.getCards();
    }
}
