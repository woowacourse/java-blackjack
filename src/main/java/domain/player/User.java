package domain.player;

import domain.CardPossessor;
import domain.card.HandCards;

public class User extends BlackJackPlayer {

    public User(CardPossessor cards) {
        super(cards);
    }

    @Override
    public boolean canDrawMore() {
        return false;
    }
}
