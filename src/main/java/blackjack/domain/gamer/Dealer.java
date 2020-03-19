package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import static blackjack.domain.rule.Score.SCORES;

public class Dealer extends Gamer {

    private static final int DRAW_THRESHOLD = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canDrawCard() {
        if (handScore().compareTo(SCORES.get(DRAW_THRESHOLD)) > 0) {
            return false;
        }
        return true;
    }

    public String getName() {
        return DEALER_NAME;
    }

    public Card getOpenCard() {
        return hand.getCardStatus().get(0);
    }
}