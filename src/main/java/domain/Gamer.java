package domain;

import java.util.List;

public class Gamer {
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
        return name.getName().equals(test);
    }

    public Name getName() {
        return name;
    }

    public int getTotalScore() {
        return hand.getResultScore();
    }
}
