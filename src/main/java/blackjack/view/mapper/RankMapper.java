package blackjack.view.mapper;

import blackjack.domain.card.Rank;
import java.util.Arrays;

public enum RankMapper {

    ACE(Rank.ACE, "A"),
    TWO(Rank.TWO, "2"),
    THREE(Rank.THREE, "3"),
    FOUR(Rank.FOUR, "4"),
    FIVE(Rank.FIVE, "5"),
    SIX(Rank.SIX, "6"),
    SEVEN(Rank.SEVEN, "7"),
    EIGHT(Rank.EIGHT, "8"),
    NINE(Rank.NINE, "9"),
    TEN(Rank.TEN, "10"),
    JACK(Rank.JACK, "J"),
    KING(Rank.KING, "K"),
    QUEEN(Rank.QUEEN, "Q");

    private final Rank rank;
    private final String rankName;

    RankMapper(Rank rank, String rankName) {
        this.rank = rank;
        this.rankName = rankName;
    }

    public static RankMapper findByRank(Rank rank) {
        return Arrays.stream(values())
                .filter(rankMapper -> rankMapper.rank == rank)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 등급이 없습니다."));
    }

    public String getRankName() {
        return rankName;
    }
}
