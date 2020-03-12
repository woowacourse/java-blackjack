package blackjack.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DrawRequestTest {

    @DisplayName("입력한 커맨드가 올바르지 않은 경우")
    @ParameterizedTest
    @CsvSource(value = {"Y", "N", "asdf"})
    void isDrawWrong(String wrongCommand) {
        assertThatThrownBy(() -> new DrawRequest(wrongCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%s는 올바른 요청이 아닙니다.", wrongCommand);
    }

    @DisplayName("입력한 커맨드가 널이거나 비어있는 경우")
    @ParameterizedTest
    @NullAndEmptySource
    void isDrawNullEmpty(String wrongCommand) {
        assertThatThrownBy(() -> new DrawRequest(wrongCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%s는 올바른 요청이 아닙니다.", wrongCommand);
    }

    @DisplayName("입력한 커맨드가 y 면 true, n 이면 false")
    @ParameterizedTest
    @CsvSource(value = {"y,true", "n,false"})
    void test(String command, boolean result) {
        DrawRequest drawRequest = new DrawRequest(command);

        assertThat(drawRequest.isDraw()).isEqualTo(result);
    }
}