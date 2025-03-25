package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerNameTest {

    @ParameterizedTest
    @DisplayName("이름이 빈 문자열이면 예외가 발생한다.")
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void should_throw_exception_when_name_is_blank(String playerName) {
        // when & then
        assertThatThrownBy(() -> new PlayerName(playerName));
    }
}
