package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Player {
    private static final int BUST_THRESHOLD = 21;

    protected final Hand hand;
    private final Name name;

    public Player(final String name) {
        this.hand = new Hand();
        this.name = new Name(name);
    }

    public void addCard(final Card card) {
        hand.add(card);
    }

    public boolean isAlive() {
        return hand.getSum() <= BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return hand.getAllCards();
    }

    public int getScore() {
        return hand.getSum();
    }

    public String getName() {
        return name.getValue();
    }
}
