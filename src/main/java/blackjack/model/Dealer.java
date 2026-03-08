package blackjack.model;

import java.util.List;

public class Dealer extends Participant {
    private static final int MIN_CARD_SUM = 16;

    @Override
    public boolean canReceive(int score) {
        return score <= MIN_CARD_SUM;
    }

    public String getInitialCards() {
        return cards.getFirst().getCardName();
    }
}
