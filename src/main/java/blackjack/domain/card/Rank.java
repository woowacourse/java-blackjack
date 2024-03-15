package blackjack.domain.card;

import java.util.List;

public enum Rank {
    TWO(List.of(2)),
    THREE(List.of(3)),
    FOUR(List.of(4)),
    FIVE(List.of(5)),
    SIX(List.of(6)),
    SEVEN(List.of(7)),
    EIGHT(List.of(8)),
    NINE(List.of(9)),
    TEN(List.of(10)),
    JACK(List.of(10)),
    QUEEN(List.of(10)),
    KING(List.of(10)),
    ACE(List.of(1, 11));

    private final List<Integer> scores;

    Rank(List<Integer> scores) {
        this.scores = scores;
    }

    public int get(int index) {
        validateIndexRange(this, index);
        return scores.get(index);
    }

    private void validateIndexRange(Rank rank, int index) {
        if (rank != ACE && index != 0) {
            throw new IllegalArgumentException("ACE를 제외한 Rank는 인덱스가 0이여야 합니다.");
        }
        if (rank == ACE && !(index == 0 || index == 1)) {
            throw new IllegalArgumentException("ACE는 인덱스가 0이거나 1이여야 합니다.");
        }
    }
}
