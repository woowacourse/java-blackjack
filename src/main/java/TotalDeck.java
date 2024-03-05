import java.util.Stack;
import java.util.List;

public class TotalDeck {
    private final Stack<Card> totalDeck;

    public TotalDeck(List<Card> cards){
        totalDeck = new Stack<>();
        totalDeck.addAll(cards);
    }

    public Card getNewCard() {
        return totalDeck.pop();
    }
}
