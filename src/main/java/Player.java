public class Player {
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
}
