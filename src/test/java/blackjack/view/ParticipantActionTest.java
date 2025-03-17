package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("참가자 행동 테스트")
class ParticipantActionTest {

    @DisplayName("y를 입력하면 HIT가 반환된다.")
    @Test
    void fromHitTest() {
        // given
        String input = "y";

        // when
        ParticipantAction action = ParticipantAction.from(input);

        // then
        assertThat(action)
                .isEqualTo(ParticipantAction.HIT);
    }

    @DisplayName("n을 입력하면 STAND가 반환된다.")
    @Test
    void fromStandTest() {
        // given
        String input = "n";

        // when
        ParticipantAction action = ParticipantAction.from(input);

        // then
        assertThat(action)
                .isEqualTo(ParticipantAction.STAND);
    }

    @DisplayName("y, n 이외의 입력이 들어오면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c", "d", "e"})
    void fromInvalidInputTest(String input) {
        // when, then
        assertThatCode(() -> ParticipantAction.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 입력이 아닙니다. 입력: %s".formatted(input));
    }
}
