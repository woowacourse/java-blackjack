package domain;

public class Dealer extends Participant {

    private Deck deck;

    public Dealer() {
        super(new Name("딜러"));
        deck = new Deck();
    }

    public boolean shouldHit() {
        return hands.calculateScore() <= 16;
    }

    public Card draw() {
        return deck.draw();
    }
}
