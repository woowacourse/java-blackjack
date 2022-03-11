package domain;

import domain.card.CanAddCardThreshold;

public class Player extends User {
    public Player() {
        super(CanAddCardThreshold.PLAYER_THRESHOLD);
    }
}
