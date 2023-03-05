package domain.user;

import domain.Hand;

public class Player extends User {

    public Player(String nameValue) {
        super.userName = new PlayerName(nameValue);
        super.hand = new Hand();
    }

    public Player(String nameValue, Hand hand) {
        super.userName = new PlayerName(nameValue);
        super.hand = hand;
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() < BLACKJACK_SCORE;
    }
}
