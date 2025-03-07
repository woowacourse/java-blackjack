package domain;

public class Participant {
    private final CardHand hand;

    public Participant() {
        hand = new CardHand();
    }

    public Participant(final CardHand hand) {
        this.hand = hand;
    }
    
    public void pickCardOnFirstHandOut(final Deck deck) {
        pickCard(deck);
        pickCard(deck);
    }

    public void pickCard(final Deck deck) {
        final Card card = deck.pickCard();
        hand.add(card);
    }

    public int calculateAllScore() {
        return hand.calculateAllScore();
    }

    public CardHand getCardHand() {
        return hand;
    }
}

