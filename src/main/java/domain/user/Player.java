package domain.user;

import domain.CardHand;

public class Player extends AbstractUser {

    public Player(String nameValue) {
        super(nameValue);
    }

    public Player(String nameValue, CardHand cardHand) {
        super(nameValue, cardHand);
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() < BLACKJACK_SCORE;
    }
}
