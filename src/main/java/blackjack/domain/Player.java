package blackjack.domain;

public class Player {
    public static final int BLACKJACK_NUMBER = 21;

    private final PlayerName name;
    private final Cards cards;

    public Player(final PlayerName name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public int calculate() {
        int sum = cards.sum();
        int aceCount = cards.countAce();

        while (sum > BLACKJACK_NUMBER && aceCount > 0) {
            sum = sum - 10;
            aceCount--;
        }
        return sum;
    }

    public boolean isBlackjack() {
       return calculate() == BLACKJACK_NUMBER;
    }
}
