package domain;

import java.util.List;
public abstract class Gamer {

    private final Name name;
    protected final Hand hand;

    public Gamer(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public void pickTwoCards(Deck deck) {
        hand.add(deck.draw());
        hand.add(deck.draw());
    }
    abstract public int hit(Deck deck);

    public boolean isName(String comparedName) {
        return name.name().equals(comparedName);
    }

    public String getName() {
        return name.name();
    }

    public int getTotalScore() {
        return hand.getResultScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}
