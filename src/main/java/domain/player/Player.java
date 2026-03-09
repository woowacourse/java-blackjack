package domain.player;

import domain.card.Card;
import java.util.List;

public class Player {

    private final Name name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        this.name = new Name(name);
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public List<Card> getCards() {
        return getHand().getCards();
    }

    public void addHand(Card card) {
        hand.add(card);
    }

    public String getName() {
        return name.getName();
    }

    public int getTotalScore() {
        return hand.getTotalScore();
    }
}
