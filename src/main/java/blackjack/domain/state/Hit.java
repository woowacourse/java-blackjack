package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit implements State {
    public Hit(Cards cards) {

    }

    @Override
    public State hit(Card card) {
        return null;
    }
}
