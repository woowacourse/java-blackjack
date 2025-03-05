package domain;

public record Player(
        CardHand cardHand
) {

    public Player() {
        this(new CardHand());
    }

    public void pickCard(final Deck deck) {
        final Card card = deck.pickCard();
        cardHand.add(card);
    }

    public boolean isPickCard() {
        return false;
    }
}
