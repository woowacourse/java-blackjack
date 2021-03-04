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
        return !super.isBustCondition() && super.getScore() < UserDeck.BLACK_JACK_NUMBER;
    }

    public String getName() {
        return name;
    }
}
