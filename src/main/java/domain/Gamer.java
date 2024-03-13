package domain;

import domain.constant.GamerResult;
import java.util.List;
import java.util.stream.IntStream;

public abstract class Gamer {

    private final Hand hand;
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

    abstract public boolean canHit();

    public int hit(Deck deck) {
        hand.add(deck.draw());
        return 1;
    }

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

    public GamerResult judge(Gamer opponent) {
        return this.hand.judge(opponent.hand);
    }
}
