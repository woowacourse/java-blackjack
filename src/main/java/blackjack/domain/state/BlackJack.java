package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class BlackJack extends Finished {
    public static final int BLACKJACK_NUMBER = 21;

    public BlackJack(Cards cards) {
        super(cards);
    }
}
