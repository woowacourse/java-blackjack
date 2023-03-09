package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPocket;
import java.util.List;

public abstract class Participant {

    private final CardPocket cardPocket;
    private int cachedScore;

    Participant() {
        cardPocket = CardPocket.empty();
    }

    void drawInitialCard(final Card first, final Card second) {
        drawCard(first);
        drawCard(second);
    }

    public boolean hasBlackJack() {
        return cardPocket.isBlackJack();
    }

    void drawCard(final Card card) {
        cardPocket.addCard(card);
        cachedScore = cardPocket.calculateScore();
    }

    public int currentScore() {
        return cachedScore;
    }

    public List<Card> getCards() {
        return cardPocket.getCards();
    }

    public abstract boolean isDrawable();
}
