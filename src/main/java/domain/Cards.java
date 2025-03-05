package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<Integer> getCoordinateSums() {
        return getCoordinateSumsByDfs(0, 0, new HashSet<>());
    }

    private Set<Integer> getCoordinateSumsByDfs(int index, int sum, Set<Integer> result) {
        if (index == cards.size()) {
            result.add(sum);
            return result;
        }

        Card card = cards.get(index);
        for (int coordinateNumber : card.getCoordinateNumbers()) {
            result = getCoordinateSumsByDfs(index + 1, sum + coordinateNumber, result);
        }
        return result;
    }

    public List<Card> getCards() {
        return cards;
    }
}
