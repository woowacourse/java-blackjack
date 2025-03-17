package domain.state;

import domain.card.Cards;

public class Blackjack extends Finished {

    public static final int BLACKJACK_CARD_SIZE = 2;
    public static final int BLACKJACK_SUM = 21;

    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public StateType type() {
        return StateType.BLACKJACK;
    }
}
