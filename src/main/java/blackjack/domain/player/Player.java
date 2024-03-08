package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Player {
    protected final Hand hand;
    private final Name name;

    public Player(final String name) {
        this.hand = new Hand();
        this.name = new Name(name);
    }

    public void addCard(final Card card) {
        hand.add(card);
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
