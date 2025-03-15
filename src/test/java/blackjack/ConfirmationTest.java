package blackjack;

import blackjack.view.Confirmation;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ConfirmationTest {
    static Stream<Arguments> 입력값에_해당하는_Confirmation을_찾는_테스트케이스() {
        return Stream.of(
                Arguments.of("y", Confirmation.Y),
                Arguments.of("n", Confirmation.N)
        );
    }

    @ParameterizedTest
    @MethodSource("입력값에_해당하는_Confirmation을_찾는_테스트케이스")
    void 입력값을_통해_y_혹은_n을_찾을_수_있다(String input, Confirmation confirmation) {
        // then
        assertThat(Confirmation.find(input))
                .isEqualTo(confirmation);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"Y", "N", "dasdsad"})
    void 입력값이_y나_n이_아니면_예외를_던진다(String input) {
        assertThatThrownBy(() -> Confirmation.find(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y, n만 입력 가능합니다.");
    }
}
