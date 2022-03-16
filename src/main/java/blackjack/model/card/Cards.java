package blackjack.model.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    public static final int START_CARD_COUNT = 2;

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

    public List<String> getCardGroup() {
        return values.stream()
                .map(card -> card.getNumberOfString() + card.getSymbol())
                .collect(Collectors.toUnmodifiableList());
    }

    public Cards getCopyInstance() {
        return new Cards(values);
    }
}
