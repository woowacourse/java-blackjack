package domain.state;

import domain.card.Cards;

public class Blackjack extends Finished {

    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public StateType type() {
        return StateType.BLACKJACK;
    }
}
