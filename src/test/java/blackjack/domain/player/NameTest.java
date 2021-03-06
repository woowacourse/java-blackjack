package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {
    @DisplayName("이름 유효성 검사 - 유효한 이름")
    @ParameterizedTest
    @ValueSource(strings = {"딜러", " jason ", " pobi"})
    void validNames(String nameInput) {
        assertThatCode(() ->
            assertThat(new Name(nameInput).getName()).isEqualTo(nameInput.trim()))
            .doesNotThrowAnyException();
    }

    @DisplayName("이름 유효성 검사 - 유효하지 않은 이름")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void invalidNames(String nameInput) {
        assertThatThrownBy(() -> new Name(nameInput))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름 유효성 검사 - null 테스트")
    @Test
    void invalidNullName() {
        assertThatThrownBy(() -> new Name(null))
            .isInstanceOf(IllegalArgumentException.class);
    }
}