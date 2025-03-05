package domain;

public record Dealer(CardHand hand) {

    public Dealer() {
        this(new CardHand());
    }

    public void pickUpCard(Deck deck) {
        final Card card = deck.pickCard();
        hand.add(card);
    }

    public boolean isPickCard() {
        return calculateAllScore() <= 16;
    }

    public int calculateAllScore() {
        return hand.calculateAllScore();
    }
}
