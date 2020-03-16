package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import static blackjack.domain.rule.Score.SCORES;

public class Dealer extends Gamer {

    private static final int DRAW_THRESHOLD = 16;

    @Override
    public boolean canDrawCard() {
        if (handScore().compareTo(SCORES.get(DRAW_THRESHOLD)) > 0) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "딜러";
    }

    public Card getOpenCard() {
        return hand.getCardStatus().get(0);
    }
}