package domain.user;

import domain.deck.UserDeck;

public class Player extends User {
    public Player(Name name) {
        super(new UserDeck(), name);
    }
}
