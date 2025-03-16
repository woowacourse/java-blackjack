package domain.card;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Card extends TrumpCard {

    private static final Map<Rank, List<Integer>> RANK_TO_SCORES = Map.ofEntries(
            Map.entry(Rank.ACE, List.of(1, 11)),
            Map.entry(Rank.TWO, List.of(2)),
            Map.entry(Rank.THREE, List.of(3)),
            Map.entry(Rank.FOUR, List.of(4)),
            Map.entry(Rank.FIVE, List.of(5)),
            Map.entry(Rank.SIX, List.of(6)),
            Map.entry(Rank.SEVEN, List.of(7)),
            Map.entry(Rank.EIGHT, List.of(8)),
            Map.entry(Rank.NINE, List.of(9)),
            Map.entry(Rank.TEN, List.of(10)),
            Map.entry(Rank.JACK, List.of(10)),
            Map.entry(Rank.QUEEN, List.of(10)),
            Map.entry(Rank.KING, List.of(10))
    );

    private boolean isOpened;

    public Card(Rank rank, Suit suit) {
        super(rank, suit);
        isOpened = false;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void open() {
        isOpened = true;
    }

    public List<Integer> scores() {
        return RANK_TO_SCORES.get(rank);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Card card)) {
            return false;
        }
        return isOpened == card.isOpened && Objects.equals(RANK_TO_SCORES, card.RANK_TO_SCORES);
    }

    @Override
    public int hashCode() {
        return Objects.hash(RANK_TO_SCORES, isOpened);
    }
}
