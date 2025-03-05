package domain;

public record Player(
        CardHand cardHand
) {

    public Player() {
        this(new CardHand());
    }

    public void pickCard(final Deck deck) {

    }
}
