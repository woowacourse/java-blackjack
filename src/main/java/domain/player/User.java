package domain.player;

import domain.AnswerType;
import domain.card.Card;
import domain.card.Cards;

public class User extends Player {
    private String name;

    public User(String name, Card... cards) {
        super(cards);
        this.name = name;
    }

    @Override
    public void insertCard(Cards cards) {
        this.cards.add(cards.pop());
    }

    public String getName() {
        return name;
    }
}
