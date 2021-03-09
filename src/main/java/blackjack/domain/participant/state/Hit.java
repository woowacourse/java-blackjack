package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.List;

public class Hit extends Running {

    private static final int BLACKJACK = 21;

    public Hit(final List<Card> cards) {
        super(cards);
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
}
