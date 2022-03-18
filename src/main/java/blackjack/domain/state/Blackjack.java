package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Blackjack extends TurnFinished {

    Blackjack(Cards cards) {
        super(cards);
    }
}
