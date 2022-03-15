package blackjack.model.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int START_CARD_COUNT = 2;

    private final List<Card> values;

    public Cards() {
        this.values = new ArrayList<>();
    }

    private Cards(List<Card> values) {
        this.values = new ArrayList<>(values);
    }

    public Cards add(final Card card) {
        values.add(card);
        return new Cards(values);
    }

    public int sumScore() {
        return CardScoreTotalizer.sum(values);
    }

    public boolean hasOnlyStartCards() {
        return values.size() == START_CARD_COUNT;
    }

    public boolean isBlackjack() {
        return sumScore() == 21 && values.size() == 2;
    }

    public List<String> getValues() {
        return values.stream()
                .map(card -> card.getNumberOfString() + card.getSymbol())
                .collect(Collectors.toUnmodifiableList());
    }
}
