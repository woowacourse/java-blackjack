package blackjack.domain.rule;

import blackjack.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HitCommandTest {

    @DisplayName("y를 입력하면 YES 커맨드를 반환한다")
    @Test
    void createYesCommandTest() {
        // given
        String input = "y";

        // when
        HitCommand command = HitCommand.from(input);

        // then
        assertThat(command).isEqualTo(HitCommand.YES);
    }

    @DisplayName("y를 입력하면 YES 커맨드를 반환한다")
    @Test
    void createNoCommandTest() {
        // given
        String input = "n";

        // when
        HitCommand command = HitCommand.from(input);

        // then
        assertThat(command).isEqualTo(HitCommand.NO);
    }

    @DisplayName("y 또는 n이 아닌 값을 입력하면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1", "123", "yyy", " "})
    void invalidInputExceptionTest(String input) {
        // when & then
        assertThatThrownBy(() -> HitCommand.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT.getMessage());
    }
}
