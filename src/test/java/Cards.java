import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards empty() {
        return new Cards(new ArrayList<>());
    }

    public static Cards of(List<Card> cards) {
        return new Cards(cards);
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int sum() {
        return cards.stream().mapToInt(this::getCardNumber).sum();
    }

    public List<Card> getCards() {
        return cards;
    }

    private int getCardNumber(Card card) {
        CardNumber number = card.getNumber();
        return number.getNumbers().getFirst();
    }
}
