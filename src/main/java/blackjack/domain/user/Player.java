package blackjack.domain.user;

import blackjack.domain.card.UserDeck;

public class Player extends User {

    private final String name;

    public Player(String name, UserDeck userDeck) {
        super(userDeck);
        this.name = name;
    }

    @Override
    public boolean isAvailableDraw() {
        return super.getPoint() <= UserDeck.BLACK_JACK_NUMBER;
    }
}
