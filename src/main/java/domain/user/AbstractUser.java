package domain.user;

import domain.Card;
import domain.CardHand;
import domain.Name;

abstract public class AbstractUser {
    protected static final int BLACKJACK_SCORE = 21;

    protected final Name name;
    protected final CardHand cardHand;

    public AbstractUser(String nameValue) {
        this.name = new Name(nameValue);
        this.cardHand = new CardHand();
    }

    public AbstractUser(String nameValue, CardHand cardHand) {
        this.name = new Name(nameValue);
        this.cardHand = cardHand;
    }

    abstract public boolean canAdd();

    public String getNameValue() {
        return this.name.getValue();
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
}
