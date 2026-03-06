package domain.participant;

import domain.Hand;
import domain.Name;
import domain.card.Card;
import java.util.List;

public abstract class Participant {
    private Name name;
    private Hand hand;

    public Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void receiveInitialCards(List<Card> cards) {
        if (cards.size() != 2) {
            throw new IllegalArgumentException();
        }

        cards.forEach(card -> receive(card));
    }

    public void receive(Card card) {
        hand.add(card);
    }

    public int handSize() {
        return hand.size();
    }

    public int score() {
        return hand.score();
    }

    public String name() {
        return name.getName();
    }

    public List<Card> getAllCards() {
        return hand.getAllCards();
    }

    public abstract boolean canDraw();
}
