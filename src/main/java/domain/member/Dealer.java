package domain.member;

import domain.card.Card;

public class Dealer extends Member {

    public Dealer(String name) {
        super(name);
    }

    public Card firstCard() {
        return super.currentCards().getFirst();
    }
}
