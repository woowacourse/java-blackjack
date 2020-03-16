package domain.player;

import domain.card.Card;

public class Player extends User {

    public Player(String name, Card... cards) {
        super(cards);
        validatePlayerName(name);
        this.name = name;
    }

    @Override
    public void drawCard(Card card) {
        this.cards.add(card);
        validateDuplicateCard();
    }

    public String getName() {
        return name;
    }
}
