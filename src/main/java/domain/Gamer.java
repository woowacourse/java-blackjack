package domain;

import java.util.List;

public class Gamer {
    private static final int BUST_THRESHOLD = 21;
    private final Name name;
    private final Hand hand;

    public Gamer(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public void takeCard(Card card) {
        hand.add(card);
    }

    public boolean isName(String test) {
        return name.name().equals(test);
    }

    public String getName() {
        return name.name();
    }

    public int getTotalScore() {
        return hand.getResultScore();
    }

    public boolean isBust() {
        return (hand.getResultScore() > BUST_THRESHOLD);
    }
}
