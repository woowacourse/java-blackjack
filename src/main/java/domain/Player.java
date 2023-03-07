package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private final String name;
    private final Hand hand;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.hand = new Hand(cards);
    }

    void addCard(Card card) {
        hand.add(card);
    }

    public void drawCardFrom(Deck deck) {
        Card drawnCard = deck.draw();
        hand.add(drawnCard);
    }

    public int getScore() {
        return hand.score();
    }

    Result competeWith(Player other) {
        return hand.compareWith(other.hand);
    }

    public abstract boolean canHit();

    boolean isBusted() {
        return hand.score() > 21;
    }

    public List<Card> getCards() {
        return new ArrayList<>(hand.cards());
    }

    public String getName() {
        return name;
    }
}