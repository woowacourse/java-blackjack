package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPocket;
import blackjack.domain.card.dto.CardResponse;
import java.util.List;

public abstract class Participant {

    private final CardPocket cardPocket;

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
    }

    public int currentScore() {
        return cardPocket.calculateScore();
    }

    public List<CardResponse> getCards() {
        return cardPocket.getCards();
    }

    public abstract boolean isDrawable();
}
