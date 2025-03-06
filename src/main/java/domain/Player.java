package domain;

public class Player {

    private final String name;
    private final Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public Hand drawCardWhenStart(CardDeck cardDeck) {
        hand.drawCardWhenStart(cardDeck);
        return hand;
    }

    public Hand drawCard(CardDeck cardDeck) {
        hand.drawCard(cardDeck);
        return hand;
    }

    public int getCardsCount() {
        return hand.getCardsCount();
    }

    public int calculateTotalCardNumber() {
        return hand.calculateTotalCardNumber();
    }

    public boolean isOverBurstBound() {
        int totalCardNumber = calculateTotalCardNumber();
        return hand.isOverBurstBound(totalCardNumber);
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
