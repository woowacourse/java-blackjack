import domain.card.Card;

import java.util.List;
import java.util.Stack;

public class TotalDeck {
    private final Stack<Card> totalDeck;

    public TotalDeck(List<Card> cards) {
        totalDeck = new Stack<>();
        totalDeck.addAll(cards);
    }

    public Card getNewCard() {
        return totalDeck.pop();
    }

    public int size() {
        return totalDeck.size();
    }
}
