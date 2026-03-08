package blackjack.model;

import java.util.List;

public class Dealer extends Participant {

    @Override
    public boolean canReceive(int score) {
        return score <= 16;
    }

    public String getInitialCards() {
        return cards.getFirst().getCardName();
    }
}
