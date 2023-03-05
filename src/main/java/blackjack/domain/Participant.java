package blackjack.domain;

import java.util.List;

public abstract class Participant {

    private final CardPocket cardPocket;

    Participant() {
        cardPocket = CardPocket.empty();
    }

    public void drawInitialCard(Card first, Card second) {
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
