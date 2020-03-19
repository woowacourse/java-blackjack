package domain.user;

import domain.card.Card;
import domain.card.Cards;

public class Dealer extends User {

    private static final String NULL_OR_EMPTY_CARD_ERROR_MESSAGE = "접근이 잘못되었거나 카드의 수가 부족합니다.";

    public Card getAnyCard() {
        if (this.cards == null || this.cards.getCards().isEmpty()) {
            throw new NullPointerException(NULL_OR_EMPTY_CARD_ERROR_MESSAGE);
        }
        return this.cards
                .getCards()
                .get(0);
    }

    @Override
    public boolean isReceiveAble(){
        return !isLargerThan(Cards.MAX_SUM_FOR_DEALER_MORE_CARD);
    }

    public boolean isScoreSame(int score) {
        return this.cards.sumScores() == score;
    }
}
