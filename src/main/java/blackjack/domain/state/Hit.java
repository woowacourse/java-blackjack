package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.Cards;

public class Hit extends Running {

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards.add(card);
        if (cards.size() == Cards.BLACKJACK_CARD_SIZE && cards.getPoint() == Cards.HIGHEST_POINT) {
            return new Blackjack(cards);
        } else if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
