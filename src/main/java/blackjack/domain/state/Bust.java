package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Bust implements State {
    public Bust(Cards cards) {

    }

    @Override
    public State hit(Card card) {
        return null;
    }

    @Override
    public State stand() {
        return null;
    }
}
