package domain.user;

import domain.Hand;

public final class Player extends User {

    public Player(String nameValue) {
        this(new PlayerName(nameValue), new Hand());
    }

    public Player(String nameValue, Hand hand) {
        this(new PlayerName(nameValue), hand);
    }

    public Player(PlayerName playerName, Hand hand) {
        super.userName = playerName;
        super.hand = hand;
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() < BLACKJACK_SCORE;
    }
}
