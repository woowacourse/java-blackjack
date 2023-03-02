package domain;

import java.util.List;

public class Dealer {

    private final Name name = new Name("딜러");
    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public void pick(Card card) {
        cards.addNewCard(card);
    }

    public Cards getCards() {
        return cards;
    }

}
