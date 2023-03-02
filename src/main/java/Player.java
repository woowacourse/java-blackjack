public class Player {
    private static final int SCORE_UPPER_LIMIT = 21;

    private final Name name;
    private final CardHand cardHand;

    public Player(String nameValue, CardHand cardHand) {
        this.cardHand = cardHand;
        this.name = new Name(nameValue);
    }

    public Player(String nameValue) {
        this.cardHand = new CardHand();
        this.name = new Name(nameValue);
    }

    public int calculateScore() {
        return this.cardHand.calculateScore();
    }

    public String getNameValue() {
        return this.name.getValue();
    }

    public boolean canAdd() {
        return cardHand.calculateScore() < SCORE_UPPER_LIMIT;
    }
}
