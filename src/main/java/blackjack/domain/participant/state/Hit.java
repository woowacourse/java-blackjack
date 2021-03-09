package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.ArrayList;
import java.util.List;

public class Hit implements State {

    private static final int BLACKJACK = 21;

    private final List<Card> cards;

    public Hit(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public State draw(final Card card) {
        this.cards.add(card);
        if (isBust()) {
            return new Bust(this.cards);
        }
        return new Hit(this.cards);
    }

    private boolean isBust() {
        return this.cards.stream()
            .mapToInt(Card::getScore)
            .sum()
            > BLACKJACK;
    }

    public State stay() {
        return new Stay(this.cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
