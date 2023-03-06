package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPocket;
import java.util.List;

public abstract class Participant {

    private final CardPocket cardPocket;

    Participant() {
        cardPocket = CardPocket.empty();
    }

    public void drawInitialCard(final Card first, final Card second) {
        cardPocket.addCard(first);
        cardPocket.addCard(second);
    }

    public void drawCard(final Card card) {
        cardPocket.addCard(card);
    }

    public int currentScore() {
        return cardPocket.calculateScore();
    }

    public List<Card> getCards() {
        return cardPocket.getCards();
    }

    public abstract boolean isDrawable();
}
