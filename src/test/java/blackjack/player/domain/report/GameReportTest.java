package blackjack.player.domain.report;

import blackjack.card.domain.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameReportTest {

    private static Stream<Arguments> createValidationProvider() {
        return Stream.of(
                Arguments.of(null, GameResult.WIN, "이름이 비어있습니다."),
                Arguments.of("bebop", null, "게임 결과가 비어있습니다.")
        );
    }

    @DisplayName("이름과 결과값이 null이면 exception")
    @ParameterizedTest
    @MethodSource("createValidationProvider")
    void createValidation(String name, GameResult gameResult, String message) {
        assertThatThrownBy(() -> new GameReport(name, gameResult))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
}