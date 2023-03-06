package domain.player;

import domain.deck.Card;
import java.util.List;

public class Player {
    private final Name name;
    private final Hand hand;

    public Player(final String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void drawCard(final Card card) {
        hand.addCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public Name getName() {
        return name;
    }

    public int getScore() {
        return hand.score();
    }
}
