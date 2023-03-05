package domain.user;

import domain.CardHand;

public class Player extends User {

    public Player(String nameValue) {
        super.userName = new PlayerName(nameValue);
        super.cardHand = new CardHand();
    }

    public Player(String nameValue, CardHand cardHand) {
        super.userName = new PlayerName(nameValue);
        super.cardHand = cardHand;
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() < BLACKJACK_SCORE;
    }
}
