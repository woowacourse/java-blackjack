package domain.player;

import domain.card.Card;
import domain.card.Cards;

public class Player extends User {
    private String name;

    public Player(String name, Card... cards) {

        super(cards);
        this.name = name;
    }

    @Override
    public void insertCard(Cards cards) {
        this.cards.add(cards.pop());
        validateDuplicateCard();
    }

    public String getName() {
        return name;
    }
}
