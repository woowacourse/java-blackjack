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
        int notASum = cards.stream()
                .filter(card -> card.getNumber() != CardNumber.A)
                .mapToInt(card -> card.getNumber().getNumbers().getFirst())
                .sum();

        List<List<Integer>> choices = cards.stream()
                .filter(card -> card.getNumber() == CardNumber.A)
                .map(card -> card.getNumber().getNumbers()).toList();

        if (notASum + choices.size() > 21) {
            return notASum + choices.size();
        }

        int aSum = dfs(choices, 0, notASum);

        return aSum;
    }

    private int dfs(List<List<Integer>> choicesList, int index, int sum) {
        if (index == choicesList.size()) {
            return sum;
        }
        int max = 0;
        for (int choice : choicesList.get(index)) {
            int result = Math.max(dfs(choicesList, index + 1, sum + choice), max);
            if (result <= 21) {
                max = result;
            }
        }
        return max;
    }

    public List<Card> getCards() {
        return cards;
    }

    private int getCardNumber(Card card) {
        CardNumber number = card.getNumber();
        return number.getNumbers().getFirst();
    }
}
