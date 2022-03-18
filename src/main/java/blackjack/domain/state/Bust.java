package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public final class Bust extends TurnFinished {
    Bust(Cards cards) {
        super(cards);
    }
}
