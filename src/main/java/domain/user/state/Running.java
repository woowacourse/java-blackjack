package domain.user.state;

import domain.card.Card;
import domain.user.Cards;

public class Running extends State {

    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public State hit(Card card) {
        cards.addCard(card);
        if (cards.isMaxScore()) {
            return new Stay(cards);
        }
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return this;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
