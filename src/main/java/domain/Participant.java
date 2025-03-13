package domain;

public abstract class Participant {

    protected final Hand hand;

    protected Participant(Hand hand) {
        this.hand = hand;
    }

    public Hand getCards() {
        return hand;
    }

    public void addCards(Hand receivedHand) {
        hand.addAll(receivedHand);
    }

    public void addCard(Card receivedCard) {
        hand.add(receivedCard);
    }

    public int calculateCardsSum() {
        return hand.calculateSum();
    }
}
