package domain;

public record Player(
    CardHand hand
) {

    public Player() {
        this(new CardHand());
    }

    public void pickCard(final Deck deck) {
        final Card card = deck.pickCard();
        hand.add(card);
    }

    public boolean isPickCard() {
        return calculateAllScore() <= 21;
    }

    public int calculateAllScore() {
        return hand.calculateAllScore();
    }
}
