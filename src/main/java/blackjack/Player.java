package blackjack;


public class Player {
    private static final Integer RECEIVE_SIZE = 21;

    private final Name name;
    private final Cards cards;

    public Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public int determineMaxSum() {
        int sum = cards.sum();
        if (cards.containAce() && sum <= 11) {
            return sum + 10;
        }
        return sum;
    }

    public boolean isReceivable() {
        return cards.sum() < RECEIVE_SIZE;
    }
}
