abstract public class AbstractUser {
    protected static final int BLACKJACK_SCORE = 21;

    protected final Name name;
    protected final CardHand cardHand;

    public AbstractUser(String nameValue) {
        this.name = new Name(nameValue);
        this.cardHand = new CardHand();
    }

    public AbstractUser(String nameValue, CardHand cardHand) {
        this.name = new Name(nameValue);
        this.cardHand = cardHand;
    }

    public String getNameValue() {
        return this.name.getValue();
    }

    public int calculateScore() {
        return this.cardHand.calculateScore();
    }

    public boolean isBlackjack() {
        return cardHand.calculateScore() == BLACKJACK_SCORE;
    }
}
