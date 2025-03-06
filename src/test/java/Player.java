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

    public Hand getHand() {
        return hand;
    }

    public int calculateTotalCardNumber() {
        return hand.calculateTotalCardNumber();
    }
}
