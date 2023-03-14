package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NameTest {

    @Test
    @DisplayName("1자 미만의 이름은 만들 수 없다")
    void validateLess() {
        assertThatThrownBy(() -> new Name("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1자 이상의 이름은 만들 수 있다")
    void validateOne() {
        assertThatCode(() -> new Name("1")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("5자 이하의 이름은 만들 수 있다")
    void validateFive() {
        assertThatCode(() -> new Name("55555")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("5자 초과의 이름은 만들 수 없다")
    void validateOver() {
        assertThatThrownBy(() -> new Name("666666")).isInstanceOf(IllegalArgumentException.class);
    }
}
