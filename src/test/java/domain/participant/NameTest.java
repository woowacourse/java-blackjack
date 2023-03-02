package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    @ValueSource(strings = {"Dino99", "#", "A#", "seongha@!", "디노!"})
    @DisplayName("이름이 영어와 한글로 이루어져 있지 않으면 예외 처리한다.")
    void shouldThrowDoesNotEnglish(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Name.NAME_FORMAT_ERROR_MESSAGE);
    }
}
