package blackjack.domain.status;

import blackjack.domain.card.Card;

public class Bust extends State {
    public Bust(Card... cards) {
        super(cards);
    }
}
