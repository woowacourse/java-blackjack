package model.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @DisplayName("이름이 공백이면 예외가 발생한다.")
    @Test
    void validateBlankName() {
        assertThatThrownBy(() -> new Name("")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 null이면 예외가 발생한다.")
    @Test
    void validateNullName() {
        assertThatThrownBy(() -> new Name(null)).isInstanceOf(IllegalArgumentException.class);
    }

}
