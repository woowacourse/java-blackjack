package domain.member;

import domain.card.Card;
import java.util.List;

public abstract class Member {

    private final Hand hand;
    private final Name name;

    public Member(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public abstract List<Card> showFirstCards();

    public abstract boolean isDealer();

    public int handValue() {
        return hand.calculateTotalValue();
    }

    public List<Card> handCards() {
        return hand.getCards();
    }

    public void receiveCard(Card card) {
        hand.appendCard(card);
    }

    public boolean hasName(String name) {
        return this.name.isName(name);
    }

    public String getName() {
        return name.getValue();
    }
}
