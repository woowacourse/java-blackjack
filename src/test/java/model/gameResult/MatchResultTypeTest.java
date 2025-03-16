package model.gameResult;

import gameResult.MatchResultType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MatchResultTypeTest {
    @DisplayName("반대되는 MatchResult 반환하는 지")
    @ParameterizedTest
    @MethodSource("makeMatchResultAndReverseResult")
    void calculateReverseResultTest(MatchResultType result, MatchResultType reverseResult) {
        //given
        MatchResultType matchResultType = result.calculateReverseResult();
        //when
        //then
        Assertions.assertThat(matchResultType).isEqualTo(reverseResult);
    }

    private static Stream<Arguments> makeMatchResultAndReverseResult() {
        return Stream.of(
                Arguments.arguments(
                        MatchResultType.WIN,
                        MatchResultType.LOSE
                ),
                Arguments.arguments(
                        MatchResultType.LOSE,
                        MatchResultType.WIN
                ),
                Arguments.arguments(
                        MatchResultType.DRAW,
                        MatchResultType.DRAW
                )
        );
    }
}
