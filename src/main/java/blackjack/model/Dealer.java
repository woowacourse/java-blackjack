package blackjack.model;

public class Dealer {

    private final ReceivedCards receivedCards;

    public Dealer() {
        this.receivedCards = new ReceivedCards();
    }

    public void putCard(NormalCard normalCard) {
        receivedCards.receive(normalCard);
    }

    public ReceivedCards getReceivedCards() {
        return receivedCards;
    }
}
