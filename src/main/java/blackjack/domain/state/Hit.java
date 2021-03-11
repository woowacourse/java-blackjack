package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends State {

    public Hit(){
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

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double earningRate() {
        return 1;
    }

}
