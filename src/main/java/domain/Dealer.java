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
        final int score = calculateAllScore();
        if (score <= 16) {
            return true;
        }
        return false;
    }

    public int calculateAllScore() {
        return hand.calculateAllScore();
    }
}
