package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class HitCommandTest {
    @DisplayName("y, n외에 다른 명령어를 입력할 수 없다")
    @ParameterizedTest
    @ValueSource(strings = {"Y", "N", "end"})
    void validateNotAllowedCommand(String command) {
        assertThatThrownBy(() -> HitCommand.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y또는 n만 입력할 수 있습니다.");
    }
}
