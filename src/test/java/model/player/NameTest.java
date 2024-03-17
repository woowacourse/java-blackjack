package model.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @DisplayName("이름이 공백이면 예외가 발생한다.")
    @Test
    void validateBlankName() {
        Assertions.assertThatThrownBy(() -> new Name("") {
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 null이면 예외가 발생한다.")
    @Test
    void validateNullName() {
        Assertions.assertThatThrownBy(() -> new Name(null) {
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
