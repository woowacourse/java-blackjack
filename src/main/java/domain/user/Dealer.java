package domain.user;

import domain.card.Card;
import domain.card.Cards;

public class Dealer extends User {
    public Card getAnyCard() {
        return this.cards
            .getAnyCard();
    }

    public boolean isScoreSame(int score) {
        return this.cards.sumScores() == score;
    }

    @Override
    public boolean canReceiveCard() {
        if (!this.isLargerThan(Cards.MAX_SUM_FOR_DEALER_MORE_CARD)) {
            return true;
        }
        return false;
    }
}
