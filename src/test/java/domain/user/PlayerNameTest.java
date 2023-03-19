package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PlayerNameTest {

    @Test
    @DisplayName("1자 미만의 이름은 만들 수 없다")
    void validateLess() {
        assertThatThrownBy(() -> new PlayerName("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Nested
    @DisplayName("1자와 5자 사이의 이름은 만들 수 있다")
    class properCase {
        @ParameterizedTest(name = "{0}일 때")
        @ValueSource(strings = {"1", "12345", "123"})
        void validateAtProperCase(String target) {
            assertThatCode(() -> new PlayerName(target))
                    .doesNotThrowAnyException();
        }
    }

    @Test
    @DisplayName("5자 초과의 이름은 만들 수 없다")
    void validateOver() {
        assertThatThrownBy(() -> new PlayerName("666666")).isInstanceOf(IllegalArgumentException.class);
    }
}
