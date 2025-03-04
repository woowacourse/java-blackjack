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
        final int sum = cards.stream().mapToInt(this::getCardNumber).sum();
        boolean isContainAce = false;
        for (Card card : cards) {
            if (card.getNumber() == CardNumber.A) {
                isContainAce = true;
            }
        }
        if (sum > 21 && isContainAce) {
            int result = cards.stream()
                    .filter(card -> card.getNumber() != CardNumber.A)
                    .mapToInt(this::getCardNumber)
                    .sum();
            result += CardNumber.A.getNumbers().get(1);
            return result;
        }
        return sum;
    }

    public List<Card> getCards() {
        return cards;
    }

    private int getCardNumber(Card card) {
        CardNumber number = card.getNumber();
        return number.getNumbers().getFirst();
    }
}
