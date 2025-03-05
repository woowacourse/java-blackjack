package blackjack.domain.gambler;

import blackjack.domain.Hands;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Gambler {
    protected final Hands hands = new Hands();

    public void addCard(final Card card) {
        hands.addNewCard(card);
    }

    public int calculateSum() {
        return hands.calculateSum();
    }

    public List<Card> getCards() {
        return hands.getCards();
    }

    public boolean isBust() {
        return !hands.isSumBelow(21);
    }
}
