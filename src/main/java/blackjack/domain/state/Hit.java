package blackjack.domain.state;

import blackjack.domain.card.Card;

public class Hit extends State {
    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profitRatio() {
        throw new UnsupportedOperationException();
    }
}
