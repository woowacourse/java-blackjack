package blackjack;

public class Player {
    protected final Name name;
    protected final Cards cards;

    public Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public int calculateScore() {
        int sum = cards.sum();
        if (cards.containAce() && sum <= 11) {
            return sum + 10;
        }
        return sum;
    }

    public String getName() {
        return name.asString();
    }
}
