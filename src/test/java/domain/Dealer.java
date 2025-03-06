package domain;

public class Dealer {

    public static final int DEALER_DRAW_BOUND = 16;
    
    private final Hand hand;

    public Dealer() {
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

    public Hand getHand() {
        return hand;
    }

    public int calculateTotalCardNumber() {
        return hand.calculateTotalCardNumber();
    }

    public boolean isOverBurstBound() {
        int totalCardNumber = calculateTotalCardNumber();
        return hand.isOverBurstBound(totalCardNumber);
    }

    public boolean isOverDrawBound() {
        return calculateTotalCardNumber() > DEALER_DRAW_BOUND;
    }
}
