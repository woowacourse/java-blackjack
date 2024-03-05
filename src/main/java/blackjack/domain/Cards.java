package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> values;

    public Cards(List<Card> values) {
        this.values = new ArrayList<>(values);
    }

    public List<Card> getValues() {
        return List.copyOf(values);
    }

    public int totalScore() {
        return values.stream()
                .mapToInt(Card::getScore)
                .reduce(0, Integer::sum);
    }
}
