package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;

public class Player extends User {

    private static final int INIT_COUNT = 2;

    public Player(String name) {
        super(name);
    }

    @Override
    public List<Card> showInitCards() {
        return this.hand.getCards(INIT_COUNT);
    }
}
