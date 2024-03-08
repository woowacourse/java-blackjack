package domain.user;

import domain.deck.PlayerDeck;

public class Player extends User {
    public Player(Name name) {
        super(new PlayerDeck(), name);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }
}
