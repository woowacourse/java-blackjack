package blackjack.model.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private final List<Card> values;

    public Cards() {
        this.values = new ArrayList<>();
    }

    private Cards(List<Card> values) {
        this.values = values;
    }

    public Cards add(final Card card) {
        values.add(card);
        return new Cards(values);
    }

    public boolean isBlackjack() {
        return sumScore() == 21 && values.size() == 2;
    }

    public boolean isBust() {
        return sumScore() > 21;
    }

    public boolean isStopScore() {
        return sumScore() > 17;
    }

    public int sumScore() {
        return CardScoreTotalizer.sum(values);
    }

    public List<String> getValues() {
        return values.stream()
                .map(card -> card.getNumberOfString() + card.getSymbol())
                .collect(Collectors.toUnmodifiableList());
    }
}
