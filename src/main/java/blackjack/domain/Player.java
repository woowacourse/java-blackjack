package blackjack.domain;

public class Player {
    private final Name name;
    private final Hand hand;

    private Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Player of(Name name) {
        return new Player(name, Hand.init());
    }

    public Player receive(TrumpCard card) {
        Hand newHand = this.hand.receive(card);
        return new Player(name, newHand);
    }

    public int countCards() {
        return hand.countCards();
    }

    public int score() {
        return hand.calculateScore();
    }

    public String name() {
        return name.getName();
    }
}
