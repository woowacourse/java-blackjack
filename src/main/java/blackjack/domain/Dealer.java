package blackjack.domain;

import java.util.List;

public class Dealer extends Participant {

    private static final int BOUND_FOR_ADDITIONAL_CARD = 16;

    public Dealer(List<Card> cards) {
        super("딜러", cards);
        endTurn();
    }

    public boolean needMoreCard() {
        int score = getScore();
        return score <= BOUND_FOR_ADDITIONAL_CARD;
    }
}
