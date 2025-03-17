package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.running.Hit;
import java.util.Objects;

public abstract class Started implements State {

    protected final Cards cards;

    protected Started(Cards cards) {
        this.cards = cards;
    }

    public static State of(Card card1, Card card2) {
        Cards cards = new Cards(card1, card2);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

    @Override
    public Score calculateTotalScore() {
        return cards.calculateMaxScore();
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Started started = (Started) object;
        return Objects.equals(cards, started.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
