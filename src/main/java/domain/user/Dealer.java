package domain.user;

import domain.card.Card;
import domain.card.Cards;

public class Dealer extends User {
    public Card getFirstCard() {
        return this.cards
            .getFirstCard();
    }

    public boolean isScoreSame(int score) {
        return this.cards.sumScores() == score;
    }

    @Override
    public boolean canReceiveCard() {
        return !this.isLargerThan(Cards.MAX_SUM_FOR_DEALER_MORE_CARD);
    }
}
