package blackjack.domain;

import java.util.List;

class Dealer {

    private final Name name;
    private final Hand hand;

    public Dealer() {
        this.name = new Name("딜러");
        this.hand = new Hand();
    }

    public String getName() {
        return name.getValue();
    }

    public void draw(Card card) {
        hand.add(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
