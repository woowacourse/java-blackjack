package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPocket;
import blackjack.domain.card.Deck;
import blackjack.domain.card.dto.CardResponse;
import java.util.List;

public abstract class Participant {

    private final CardPocket cardPocket;

    Participant() {
        cardPocket = CardPocket.empty();
    }

    void drawInitialCard(final Deck deck) {
        drawCard(deck.popCard());
        drawCard(deck.popCard());
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

    List<CardResponse> getCards() {
        return cardPocket.getCards();
    }

    public abstract boolean isDrawable();
}
