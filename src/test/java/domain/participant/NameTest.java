package domain.participant;

import static org.assertj.core.api.Assertions.*;

import domain.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "po bi", " ", "   "})
    @DisplayName("이름에 빈칸이 포함되면 예외 처리한다.")
    void shouldThrowContainBlank(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Name.NAME_EMPTY_ERROR_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"가", "가A", "A1", "#", "A#"})
    @DisplayName("이름이 영어가 아니면 예외 처리한다.")
    void shouldThrowDoesNotEnglish(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Name.NAME_FORMAT_ERROR_MESSAGE);
    }
}
