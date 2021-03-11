package blackjack.domain.status;

import blackjack.domain.card.Card;

public class Hit extends State {
    public Hit(Card... cards) {
        super(cards);
    }
}
