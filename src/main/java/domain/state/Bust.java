package domain.state;

import domain.card.Cards;

public class Bust extends Finished {

    public static final int BUST_THRESHOLD = 21;

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public StateType type() {
        return StateType.BUST;
    }
}
