package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.common.ErrorMessage;
import blackjack.view.Confirmation;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ConfirmationTest {

    static Stream<Arguments> getTestCasesForFind() {
        return Stream.of(
                Arguments.of("y", Confirmation.Y),
                Arguments.of("n", Confirmation.N)
        );
    }

    @DisplayName("입력값을 통해 y 혹은 n 을 찾는다")
    @ParameterizedTest
    @MethodSource("getTestCasesForFind")
    void test1(String input, Confirmation confirmation) {
        // then
        assertThat(Confirmation.find(input))
                .isEqualTo(confirmation);
    }

    @DisplayName("입력값에 해당하는 사인이 없는 경우 예외를 던진다")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"Y", "N", "dasdsad"})
    void test2(String input) {
        assertThatThrownBy(() -> Confirmation.find(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_CONFIRMATION_INPUT.getMessage());
    }
}
