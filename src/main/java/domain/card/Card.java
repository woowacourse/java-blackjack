package domain.card;

import java.util.List;
import java.util.Map;

public class Card extends TrumpCard {

    private static final Map<Rank, List<Integer>> SCORES_BY_RANK = Map.ofEntries(
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
        return SCORES_BY_RANK.get(rank);
    }
}
