package domain.card;

import domain.card.trump.Rank;
import java.util.Arrays;
import java.util.List;

public enum BlackjackRank {
    ACE(Rank.ACE, List.of(1, 11)),
    TWO(Rank.TWO, List.of(2)),
    THREE(Rank.THREE, List.of(3)),
    FOUR(Rank.FOUR, List.of(4)),
    FIVE(Rank.FIVE, List.of(5)),
    SIX(Rank.SIX, List.of(6)),
    SEVEN(Rank.SEVEN, List.of(7)),
    EIGHT(Rank.EIGHT, List.of(8)),
    NINE(Rank.NINE, List.of(9)),
    TEN(Rank.TEN, List.of(10)),
    JACK(Rank.JACK, List.of(10)),
    QUEEN(Rank.QUEEN, List.of(10)),
    KING(Rank.KING, List.of(10));

    private final Rank rank;
    private final List<Integer> scores;

    BlackjackRank(Rank rank, List<Integer> scores) {
        this.rank = rank;
        this.scores = scores;
    }

    public static List<Integer> getScoresByRank(Rank rank) {
        return Arrays.stream(values())
                .filter(blackjackRank -> blackjackRank.rank == rank)
                .map(blackjackRank -> blackjackRank.scores)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(rank + ": 존재하지 않는 랭크입니다."));
    }
}
