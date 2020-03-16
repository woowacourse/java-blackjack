package domain.player;

import domain.card.Card;

import java.util.List;

public class User extends Player {
    public User(String name, List<Card> cards) {
        super(name, cards);
    }
}
