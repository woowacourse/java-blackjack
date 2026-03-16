package model.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {
    private static final int RANK_SIZE = 13;

    @Test
    void 랭크는_13개로_구성된다() {
        assertThat(Rank.values()).hasSize(RANK_SIZE);
    }

    @Test
    void 랭크는_실제_블랙잭의_트럼프_카드와_동일하다() {
        assertThat(Rank.values()).containsExactlyInAnyOrder(
                Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN,
                Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE);
    }

    @ParameterizedTest
    @MethodSource("provideRankAndValue")
    void 각_랭크는_옳바른_점수로_계산된다(Rank rank, int value) {
        assertThat(rank.toValue()).isEqualTo(value);
    }

    private static Stream<Arguments> provideRankAndValue() {
        return Stream.of(
                Arguments.of(Rank.TWO, 2),
                Arguments.of(Rank.THREE, 3),
                Arguments.of(Rank.FOUR, 4),
                Arguments.of(Rank.FIVE, 5),
                Arguments.of(Rank.SIX, 6),
                Arguments.of(Rank.SEVEN, 7),
                Arguments.of(Rank.EIGHT, 8),
                Arguments.of(Rank.NINE, 9),
                Arguments.of(Rank.TEN, 10),
                Arguments.of(Rank.JACK, 10),
                Arguments.of(Rank.QUEEN, 10),
                Arguments.of(Rank.KING, 10),
                Arguments.of(Rank.ACE, 11)
        );
    }
}
