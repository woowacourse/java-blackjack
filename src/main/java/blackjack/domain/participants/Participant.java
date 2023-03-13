package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;

import java.util.List;

public abstract class Participant {

    private final CardPocket cardPocket;

    protected Participant() {
        cardPocket = new CardPocket();
    }

    public void drawCard(final Card card) {
        cardPocket.addCard(card);
    }

    public Score currentScore() {
        return cardPocket.getScore();
    }

    public List<Card> getCards() {
        return cardPocket.getPossessedCards();
    }

    public boolean isBusted() {
        return cardPocket.isBusted();
    }

    public abstract boolean isDrawable();

}
