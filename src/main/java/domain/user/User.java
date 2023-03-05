package domain.user;

import domain.Card;
import domain.CardHand;
import java.util.List;

abstract public class User {
    protected static final int BLACKJACK_SCORE = 21;

    protected UserName userName;
    protected CardHand cardHand;

    abstract public boolean canAdd();

    public String getNameValue() {
        return this.userName.getValue();
    }

    public int calculateScore() {
        return this.cardHand.calculateScore();
    }

    public boolean isBlackjack() {
        return cardHand.calculateScore() == BLACKJACK_SCORE;
    }

    public void addCard(Card card) {
        if (canAdd()) {
            cardHand.add(card);
            return;
        }
        throw new IllegalStateException("카드 추가가 불가능하여 실행되지 않았습니다.");
    }

    public List<Card> getCards() {
        return this.cardHand.getCards();
    }

    public boolean isBust() {
        return this.calculateScore() > BLACKJACK_SCORE;
    }
}
