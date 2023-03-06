package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Participant {

    protected static final int BLACK_JACK_SCORE = 21;

    protected final Cards cards;

    protected Participant() {
        this(new Cards());
    }

    protected Participant(final Cards cards) {
        this.cards = cards;
    }

    public void drawCard(final Card card) {
        if (!isDrawable()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없습니다.");
        }
        cards.addCard(card);
    }

    public int getScore() {
        return cards.calculateTotalScore();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public abstract boolean isDrawable();

    public abstract boolean isDealer();

    public abstract String getName();
}
