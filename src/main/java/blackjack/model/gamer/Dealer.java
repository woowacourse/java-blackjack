package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.gameRule.GameRule;

public class Dealer extends Gamer {

    public Card getFistCard() {
        return getCards().get(0);
    }

    @Override
    public boolean canHit() {
        int score = calculateScore().getScore();
        return score <= GameRule.DEALER_HIT_MAX_SCORE;
    }
}
