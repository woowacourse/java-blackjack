package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stay;

public class Hit extends Running {

    public Hit() {
        super();
    }

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards.draw(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

}
