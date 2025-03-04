import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int sum() {
        int sum = cards.stream()
            .filter(card -> card.getCardNumber() != CardNumber.ACE)
            .mapToInt(card -> card.getCardNumber().getNumber())
            .sum();

        return sum + aceNumber(sum);
    }

    private int aceNumber(int sum) {
        // TODO: ACE 1 or 11 처리 필요.
        return 1;
    }
}

