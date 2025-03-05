package blackjack.model;

public abstract class Player {
    private final ReceivedCards receivedCards = new ReceivedCards();

    protected Player() {
    }

    public void putCard(Card card) {
        receivedCards.receive(card);
    }

    public ReceivedCards getReceivedCards() {
        return receivedCards;
    }
}
