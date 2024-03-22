package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.dto.gamer.GamerCardState;

import java.util.Collections;
import java.util.List;

public abstract class Gamer {
    public static final int BLACKJACK_CARD_SIZE = 2;
    public static final int BLACKJACK_SCORE = 21;

    protected final Cards cards;

    protected Gamer(final Cards cards) {
        this.cards = cards;
    }

    public void receiveInitCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.isBurst();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public boolean isBlackjack() {
        if (cards.size() == BLACKJACK_CARD_SIZE && cards.calculateScore() == BLACKJACK_SCORE) {
            return true;
        }
        return false;
    }

    public GamerCardState cardStatus() {
        return new GamerCardState(cards(), getScore());
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
