package blackjack.domain.status;

import blackjack.domain.card.Card;

public class Blackjack extends State {
    public Blackjack(Card... cards) {
        super(cards);
    }
}
