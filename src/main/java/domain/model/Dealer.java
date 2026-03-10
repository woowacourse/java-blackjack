package domain.model;

public class Dealer extends Person {
    private Dealer(Deck deck) {
        super(deck);
    }

    public static Dealer of(Deck deck) {
        return new Dealer(deck);
    }
}