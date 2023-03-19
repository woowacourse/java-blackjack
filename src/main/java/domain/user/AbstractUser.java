package domain.user;

import domain.Card;
import domain.CardHand;

abstract public class AbstractUser {
    protected static final int BLACKJACK_SCORE = 21;

    protected final CardHand cardHand;

    public AbstractUser() {
        this.cardHand = new CardHand();
    }

    public AbstractUser(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    abstract public boolean canAdd();

    public int calculateScore() {
        return this.cardHand.calculateScore();
    }

    public void addCard(Card... cards) {
        for (Card card : cards) {
            validateCanAdd();
            cardHand.add(card);
        }
    }

    private void validateCanAdd() {
        if (!canAdd()) {
            throw new IllegalStateException("카드 추가가 불가능하여 실행되지 않았습니다.");
        }
    }

    public CardHand getCardHand() {
        return this.cardHand;
    }

    public boolean isBust() {
        return this.cardHand.calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return cardHand.getCards().size() == 2 && cardHand.calculateScore() == 21;
    }
}
