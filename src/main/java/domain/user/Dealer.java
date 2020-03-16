package domain.user;

import domain.card.Card;

public class Dealer extends User {

    public Card getAnyCard() {
        if (this.cards == null || this.cards.getCards().isEmpty()) {
            throw new NullPointerException("접근이 잘못되었거나 카드의 수가 부족합니다.");
        }
        return this.cards
                .getCards()
                .get(0);
    }

    public boolean isScoreSame(int score) {
        return this.cards.sumScores() == score;
    }
}
