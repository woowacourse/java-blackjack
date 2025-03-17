package domain.state;

import domain.card.Cards;

public class Bust extends Finished {

    public static final int BUST_THRESHOLD = 21;

    public Bust(Cards cards) {
        super(cards);
    }

    public static boolean isBust(Cards cards) {
        return cards.computeOptimalSum() > Bust.BUST_THRESHOLD;
    }

    public static boolean isBustThreshold(Cards cards) {
        return cards.computeOptimalSum() == Bust.BUST_THRESHOLD;
    }
}
