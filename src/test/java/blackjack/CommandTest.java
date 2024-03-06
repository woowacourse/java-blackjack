package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @DisplayName("문자열로 커맨드를 생성한다.")
    @ParameterizedTest
    @CsvSource(value = {"y,YES", "n,NO"})
    void generate(String given, Command expected) {
        Command result = Command.generate(given);

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("유효하지 않는 문자열을 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"daon", "ella", "123"})
    void generate(String given) {
        assertThatThrownBy(() -> Command.generate(given))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
