package blackjack.domain;

import java.util.List;

public class Player {
    private final Name name;
    protected final Hand hand;

    public Player(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void addCards(final Card... cards) {
        for (Card card : cards) {
            hand.add(card);
        }
    }

    public List<Card> getCards() {
        return hand.getAllCards();
    }

    public boolean isDead() {
        return hand.getSum() > 21;
    }

    // TODO : 꼭 필요한 메서드일까?
    public int getScore() {
        return hand.getSum();
    }

    public String getName() {
        return name.getValue();
    }
}
