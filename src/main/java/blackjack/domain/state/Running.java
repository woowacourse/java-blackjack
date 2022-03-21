package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;

public abstract class Running implements State {

    private final Cards cards;

    Running(Cards cards) {
        this.cards = cards;
    }

    @Override
    public final State draw(Card card) {
        final Cards cards = this.cards.addCard(card);

        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public final State stay() {
        return new Stay(cards);
    }

    @Override
    public final Cards cards() {
        return cards;
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final double profit(Dealer dealer, int money) {
        throw new UnsupportedOperationException();
    }
}
