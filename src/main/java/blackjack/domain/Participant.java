package blackjack.domain;

import java.util.List;

public class Participant extends Player {

    public Participant(final String name, final List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean canAddCard() {
        return getTotalScore() <= 21;
    }
}
