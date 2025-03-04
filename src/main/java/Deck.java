import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;

    public Deck(RandomCardStrategy strategy) {
        this.cards = strategy.generateDeck();
    }

    public Stack<Card> getAll() {
        return cards;
    }
}
