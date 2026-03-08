package domain.member;

import domain.card.Card;
import java.util.List;

public class Member {

    protected final Hand hand;
    private final Name name;

    public Member(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public String name() {
        return name.getValue();
    }

    public int currentValue() {
        return hand.calculateTotalValue();
    }

    public List<Card> currentCards() {
        return hand.cards();
    }

    public void receiveCard(Card card) {
        hand.appendCard(card);
    }
}
