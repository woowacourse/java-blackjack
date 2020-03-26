package blackjack.domain.participant;

import java.util.List;

public class Dealer extends Participant {
    static final String DEALER_NAME = "딜러";
    public static final int LOWER_BOUND = 16;
    private static final int FIRST_CARD = 1;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canGetMoreCard() {
        return computeScore() <= LOWER_BOUND;
    }

    public List<String> showFirstCard() {
        return showCards().subList(0, FIRST_CARD);
    }
}
