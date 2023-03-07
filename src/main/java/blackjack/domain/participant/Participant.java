package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;
import java.util.List;

public abstract class Participant {

    protected final Cards cards;

    protected Participant() {
        this(new Cards());
    }

    protected Participant(final Cards cards) {
        this.cards = cards;
    }

    public void drawCard(final Card card) {
        cards.addCard(card);
    }

    public int getScore() {
        return cards.calculateTotalScore();
    }

    public Result getWinningStatus(final int score) {
        return null;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public abstract boolean isDrawable();

    public abstract String getName();
}
