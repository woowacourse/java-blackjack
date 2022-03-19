package blackjack.model.state.Running;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.state.Finished.Bust;
import blackjack.model.state.Finished.Stay;
import blackjack.model.state.State;

public class Hit extends Running {

    public Hit(final Cards cards) {
        super(cards);
    }

    @Override
    public State add(Card card) {
        Cards cards = this.cards.add(card);

        if (cards.isMaxScore()) {
            return new Stay(cards);
        }
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isHit() {
        return true;
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
