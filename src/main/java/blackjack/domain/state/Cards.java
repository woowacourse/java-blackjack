package blackjack.domain.state;

import blackjack.domain.card.PlayingCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cards {
    private final List<PlayingCard> values;

    public Cards(final PlayingCard... values) {
        this(Arrays.asList(values));
    }

    public Cards(final List<PlayingCard> values) {
        this.values = new ArrayList<>(values);
    }

    public void add(final PlayingCard card) {
        values.add(card);
    }

    public List<PlayingCard> list() {
        return new ArrayList<>(values);
    }

    public boolean isBlackjack() {
        return values.size() == 2 && score().isBlackjack();
    }

    public boolean isBust() {
        return score().isBust();
    }

    public Score score() {
        Score score = sum();
        final long numberOfAces = numberOfAces();
        for (int count = 0; count < numberOfAces; count++) {
            score = score.plusTenIfNotBust();
        }
        return score;
    }

    private long numberOfAces() {
        return values.stream()
                .filter(PlayingCard::isAce)
                .count()
                ;
    }

    private Score sum() {
        return new Score(values.stream()
                .mapToInt(PlayingCard::getScore)
                .sum()
        );
    }
}
