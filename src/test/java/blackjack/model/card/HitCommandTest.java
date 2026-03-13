package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HitCommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"y", "Y", "n", "N"})
    @DisplayName("HitCommand 정상 생성")
    void create_hitCommand_success(String hitCommand) {
        //when & then
        assertDoesNotThrow(() -> new HitCommand(hitCommand));
    }


    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("공백이 입력될 경우 예외 발생")
    void create_hitCommand_fail_when_empty_input(String emptyInput) {
        //when & then
        assertThatThrownBy(() -> new HitCommand(emptyInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ad", "y*"})
    @DisplayName("정해진 입력 외의 문자열을 입력할 경우 예외 발생")
    void create_hitCommand_fail_when_invalid_input(String invalidInput) {
        //when & then
        assertThatThrownBy(() -> new HitCommand(invalidInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"y", "Y"})
    @DisplayName("입력값이 y 또는 Y일 경우 true 반환")
    void isY_return_true_when_y_Y(String hitCommandInput) {
        // given
        HitCommand hitCommand = new HitCommand(hitCommandInput);

        //when
        boolean result = hitCommand.isY();

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"n", "N"})
    @DisplayName("입력값이 n 또는 N일 경우 false 반환")
    void isY_return_false_when_n_N(String hitCommandInput) {
        // given
        HitCommand hitCommand = new HitCommand(hitCommandInput);

        //when
        boolean result = hitCommand.isY();

        // then
        assertThat(result).isFalse();
    }
}