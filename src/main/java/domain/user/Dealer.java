package domain.user;

import domain.card.Card;

public class Dealer extends User {

    public Card getAnyCard() {
        return this.cards
            .getAnyCard();
    }

    public boolean isScoreSame(int score) {
        return this.cards.sumScores() == score;
    }
}
