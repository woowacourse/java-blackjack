package blackjack.domain;

import java.util.List;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final int BOUNDARY_SCORE = 16;

    public Dealer(final List<Card> cards) {
        super(NAME, cards);
    }

    @Override
    public boolean canAddCard() {
        return getTotalScore() <= BOUNDARY_SCORE;
    }
}
