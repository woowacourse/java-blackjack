package domain;

import java.util.List;
import java.util.stream.IntStream;

public abstract class Gamer {

    private final Hand hand;

    public Gamer() {
        this.hand = new Hand();
    }

    public void pickCards(Deck deck, int count) {
        IntStream.range(0, count)
                .forEach(it -> hand.add(deck.draw()));
    }

    abstract public boolean canHit();

    public int hit(Deck deck) {
        hand.add(deck.draw());
        return 1;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getTotalScore() {
        return hand.getResultScore();
    }
}
