package blackjack.domain.status;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

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
}
