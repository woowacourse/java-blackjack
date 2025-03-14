package model.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RankTest {

    @ParameterizedTest
    @DisplayName("점수가 여러개인 카드인지 판별 기능")
    @MethodSource("isMultiScoresMethodSources")
    void isMultiScores(Rank rank, Boolean expected) {

        // given
        // when
        boolean actual = rank.isMultiScores();

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> isMultiScoresMethodSources() {
        return Stream.of(
                Arguments.arguments(Rank.FIVE, Boolean.FALSE),
                Arguments.arguments(Rank.ACE, Boolean.TRUE)
        );
    }

    @ParameterizedTest
    @DisplayName("인덱스로 점수를 잘 찾는 지 판별 기능")
    @MethodSource("findScoreMethodSources")
    void findScore(Rank rank, int idx, int expected) {

        // given
        // when
        int actual = rank.findScore(idx);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> findScoreMethodSources() {
        return Stream.of(
                Arguments.arguments(Rank.FIVE, 0, 5),
                Arguments.arguments(Rank.ACE, 1, 1)
        );
    }

    @ParameterizedTest
    @DisplayName("조정 인덱스를 잘 판단해서 반환하는 지")
    @MethodSource("findAdjustOrOriginalIdxMethodSources")
    void findAdjustOrOriginalIdx(Rank rank, int originIdx, int expectedAdjustIdx) {

        // given
        // when
        int actualAdjustIdx = rank.findAdjustOrOriginalIdx(originIdx);

        // then
        Assertions.assertThat(actualAdjustIdx).isEqualTo(expectedAdjustIdx);
    }

    private static Stream<Arguments> findAdjustOrOriginalIdxMethodSources() {
        return Stream.of(
                Arguments.arguments(Rank.FIVE, 0, 0),
                Arguments.arguments(Rank.ACE, 0, 1),
                Arguments.arguments(Rank.ACE, 1, 1)
        );
    }
}
