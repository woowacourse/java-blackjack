package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("이름을 정상적으로 생성한다.")
    void createName() {
        Name name = new Name("pobi");
        assertThat(name.getName()).isEqualTo("pobi");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    @DisplayName("이름이 빈 값이면 예외가 발생한다.")
    void blankName(String input) {
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름이 null이면 예외가 발생한다.")
    void nullName() {
        assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}