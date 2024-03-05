import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDeck {
    private final List<Card> userDeck = new ArrayList<>();

    public void pushCard(Card card) {
        userDeck.add(card);
    }

    public List<Card> getUserDeck() {
        return Collections.unmodifiableList(userDeck);
    }

    public int sumCard() {
        return 11;
    }
}
