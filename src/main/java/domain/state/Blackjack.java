package domain.state;

import domain.card.Cards;

public class Blackjack extends Finished {

    public static final int BLACKJACK_CARD_SIZE = 2;
    public static final int BLACKJACK_SUM = 21;

    public Blackjack(Cards cards) {
        super(cards);
    }

    public static boolean isBlackjack(Cards cards) {
        return cards.size() == Blackjack.BLACKJACK_CARD_SIZE
                && cards.computeOptimalSum() == Blackjack.BLACKJACK_SUM;
    }
}
