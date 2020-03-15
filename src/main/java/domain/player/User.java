package domain.player;

import domain.card.Card;

public class User extends Player {
    public User(String name, Card... cards) {
        super(name, cards);
    }
}
