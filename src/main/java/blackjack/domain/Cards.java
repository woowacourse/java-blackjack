package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> value;

    public Cards(List<Card> cards) {
        this.value = new ArrayList<>(cards);
    }

    public int calculateTotalScore() {
        return value.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}
