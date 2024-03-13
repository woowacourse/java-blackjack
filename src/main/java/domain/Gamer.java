package domain;

import java.util.List;
import java.util.stream.IntStream;

public abstract class Gamer {

    protected final Hand hand;
    private final Name name;

    public Gamer(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public void pickCards(Deck deck, int count) {
        IntStream.range(0, count)
                .forEach(it -> hand.add(deck.draw()));
    }

    abstract public int hit(Deck deck);

    public boolean hasName(Name comparedName) {
        return name.equals(comparedName);
    }

    public Name getName() {
        return name;
    }

    public int getTotalScore() {
        return hand.getResultScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}
