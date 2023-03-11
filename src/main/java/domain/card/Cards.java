package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public static Cards empty() {
        return new Cards(new ArrayList<>());
    }

    public static Cards of(final Card... card) {
        List<Card> cards = new ArrayList<>(Arrays.asList(card));
        return new Cards(cards);
    }

    public int count(final Denomination denomination) {
        return (int) cards.stream()
            .filter(card -> card.isMatch(denomination))
            .count();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
