public class Player {
    private final CardHand cardHand;

    public Player(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    public int calculateScore() {
        return this.cardHand.calculateScore();
    }
}
