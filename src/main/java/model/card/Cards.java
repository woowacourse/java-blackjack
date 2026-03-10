package model.card;

import java.util.List;

public class Cards {
    private final List<Card> values;

    private Cards(List<Card> values) {
        this.values = values;
    }

    public static Cards from(List<Card> cards) {
        List<Card> copied = List.copyOf(cards);

        return new Cards(copied);
    }

    public List<Card> asList() {
        return List.copyOf(values);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "values=" + values +
                '}';
    }
}
