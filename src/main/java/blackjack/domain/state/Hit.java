package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Hit extends Running {

    Hit(Cards cards) {
        super(cards);
    }
}
