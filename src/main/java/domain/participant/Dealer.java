package domain.participant;

import domain.card.Card;

import java.util.List;
import java.util.Objects;

public class Dealer extends Participant {

    private static final int DEALER_DRAW_LIMIT = 16;

    private static final String DEALER_DEFAULT_NAME = "딜러";

    public Dealer(List<Card> cards) {
        super(DEALER_DEFAULT_NAME, cards);
    }

    public boolean isDealerHittable() {
        return cards.calculateTotalCardNumber() <= DEALER_DRAW_LIMIT;
    }

    public Card getInitialCard() {
        return cards.getInitialCard();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(cards, dealer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
