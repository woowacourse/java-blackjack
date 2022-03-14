package blackjack.model.card;

import blackjack.model.CardScoreTotalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int SCORE_LIMIT = 21;

    private final List<Card> values;

    public Cards() {
        this.values = new ArrayList<>();
    }

    public void add(Card card) {
        this.values.add(card);
    }

    public int sumScore() {
        return CardScoreTotalizer.sum(values);
    }

    public boolean isOverLimitScore() {
        return sumScore() > SCORE_LIMIT;
    }

    public boolean isSameWithLimitScore() {
        return sumScore() == SCORE_LIMIT;
    }

    public boolean hasTwoCard() {
        return values.size() == 2;
    }

    public boolean isScoreOverThan(int otherScore) {
        return sumScore() > otherScore;
    }

    public List<String> getValues() {
        return values.stream()
                .map(card -> card.getNumberOfString() + card.getSymbol())
                .collect(Collectors.toUnmodifiableList());
    }
}
