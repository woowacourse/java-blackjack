import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeckFactory {
    private CardDeckFactory() {
        throw new AssertionError();
    }

    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        Collections.shuffle(cards);
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(type, symbol));
        }
    }
}
