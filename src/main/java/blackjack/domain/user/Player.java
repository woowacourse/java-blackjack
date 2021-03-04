package blackjack.domain.user;

import blackjack.domain.card.UserDeck;

public class Player extends User {

    private static final int BLACKJACK_NUMBER = 21;
    private final String name;

    public Player(String name, UserDeck userDeck) {
        super(userDeck);
        super.DRAWABLE_NUMBER = BLACKJACK_NUMBER;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
