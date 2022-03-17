package domain.utils;

import domain.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import utils.GameResultConvertor;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultConvertorTest {
    @ParameterizedTest
    @DisplayName("게임결과 문자열로 변환 기능 테스트")
    @CsvSource(value = {"WIN,승", "LOSE,패", "DRAW,무"})
    void convertTest(final GameResult gameResult, final String expected) {
        assertThat(GameResultConvertor.convertToString(gameResult)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("게임결과를 갯수 + 문자열로 변환 기능 테스트")
    @MethodSource("provideResultWithExpectedString")
    void convertTest(final List<String> expected, final List<GameResult> results) {
        assertThat(GameResultConvertor.convertToCountWithString(results)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideResultWithExpectedString() {
        return Stream.of(
                Arguments.of(List.of("3승"), List.of(GameResult.WIN, GameResult.WIN, GameResult.WIN)),
                Arguments.of(List.of("1승", "1패"), List.of(GameResult.WIN, GameResult.LOSE)),
                Arguments.of(List.of("2패", "1무"), List.of(GameResult.DRAW, GameResult.LOSE, GameResult.LOSE)),
                Arguments.of(List.of("1승", "2패", "1무"), List.of(GameResult.WIN, GameResult.DRAW, GameResult.LOSE, GameResult.LOSE))
        );
    }
}
