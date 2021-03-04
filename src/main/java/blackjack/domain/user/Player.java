package blackjack.domain.user;

import blackjack.domain.card.UserDeck;

public class Player extends User {

    private final String name;

    public Player(String name, UserDeck userDeck) {
        super(userDeck);
        super.DRAWABLE_NUMBER = UserDeck.BLACK_JACK_NUMBER;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
