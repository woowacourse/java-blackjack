package blackjack.model.card;

import java.net.PortUnreachableException;
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

    public int sumScore() {
        return CardScoreTotalizer.sum(values);
    }

    public boolean hasTwoCard() {
        return values.size() == 2;
    }

    public int countAddedCard() {
        return values.size() - 2;
    }

    public List<String> getValues() {
        return values.stream()
                .map(card -> card.getNumberOfString() + card.getSymbol())
                .collect(Collectors.toUnmodifiableList());
    }
}
