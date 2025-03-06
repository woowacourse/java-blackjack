package domain;

public class Participant {
    private final CardHand hand;
    private final DuelHistory duelHistory;

    public Participant() {
        hand = new CardHand();
        duelHistory = new DuelHistory();
    }

    public Participant(final CardHand hand) {
        this.hand = hand;
        this.duelHistory = new DuelHistory();
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

    public DuelHistory getDuelHistory() {
        return duelHistory;
    }
}

