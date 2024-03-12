package blackjack.domain;

import static blackjack.domain.DrawDecision.NO;
import static blackjack.domain.DrawDecision.YES;
import static blackjack.domain.DrawDecision.from;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("드로우 결정 도메인 테스트")
class DrawDecisionTest {

    @DisplayName("존재하지 않는 코드명이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1", "libi", "jerry"})
    void testEnumFromInvalidCode(String code) {
        assertThatThrownBy(() -> from(code))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] y또는 n로 입력해주세요");
    }

    @DisplayName("존재하는 코드명이면 적절한 상수를 반환받는다")
    @ParameterizedTest
    @CsvSource(value = {"y, YES", "n, NO",})
    void testEnumFromValidCode(String code, DrawDecision drawDecision) {
        assertThat(from(code)).isEqualTo(drawDecision);
    }

    @DisplayName("YES인지 확인할 수 있다")
    @Test
    void testIsYes() {
        assertAll(
                () -> assertThat(YES.isYes()).isTrue(),
                () -> assertThat(NO.isYes()).isFalse()
        );
    }
}
