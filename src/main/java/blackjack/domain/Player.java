package blackjack.domain;

import java.util.List;

class Player {

    private final Name name;
    private final Hand hand;

    public Player(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void draw(Card card) {
        hand.add(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
