package state.running;

import card.Card;
import card.Cards;
import state.finished.Bust;
import state.finished.Stand;
import state.State;

public class Hit extends Running {

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards.add(card);

        if (cards.calculateScore() > 21) {
            return new Bust(cards);
        }

        return new Hit(cards);
    }

    @Override
    public State stand() {
        return new Stand(cards);
    }
}
