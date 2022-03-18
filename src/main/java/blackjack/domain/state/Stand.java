package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public final class Stand extends TurnFinished {
    Stand(Cards cards) {
        super(cards);
    }
}
