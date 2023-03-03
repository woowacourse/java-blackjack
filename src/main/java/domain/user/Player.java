package domain.user;

import domain.Card;
import domain.CardHand;

public class Player extends AbstractUser {

    public Player(String nameValue) {
        super(nameValue);
    }

    public Player(String nameValue, CardHand cardHand) {
        super(nameValue, cardHand);
    }
    public boolean canAdd() {
        return super.calculateScore() < BLACKJACK_SCORE;
    }

    public void addCard(Card card) {
        if (canAdd()) {
            super.cardHand.add(card);
            return;
        }
        throw new IllegalStateException("카드 추가가 불가능하여 실행되지 않았습니다.");
    }
}
