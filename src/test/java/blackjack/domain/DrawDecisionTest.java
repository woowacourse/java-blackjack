package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("드로우 결정 도메인 테스트")
class DrawDecisionTest {

    @DisplayName("존재하지 않는 코드명이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1", "libi", "jerry"})
    void testEnumFromInvalidCode(String code) {
        assertThatThrownBy(() -> DrawDecision.from(code))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당하는 값을 찾지 못하였습니다.");
    }

    @DisplayName("존재하는 코드명이면 적절한 상수를 반환받는다")
    @ParameterizedTest
    @MethodSource("provideEnumFromCsv")
    void testEnumFromValidCode(String code, DrawDecision drawDecision) {
        assertThat(DrawDecision.from(code)).isEqualTo(drawDecision);
    }

    private static Stream<Arguments> provideEnumFromCsv() {
        return Stream.of(
                Arguments.of("y", DrawDecision.YES),
                Arguments.of("n", DrawDecision.NO)
        );
    }
}