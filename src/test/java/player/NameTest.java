package player;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @Test
    @DisplayName("Name은 이름 문자열을 받아 생성된다.")
    void create() {
        assertThatCode(() -> new Name("폴로"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이름이 5자 초과인 경우 예외가 발생한다.")
    void crateNameFailTest() {
        assertThatThrownBy(() -> new Name("폴로랑로지다"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 1글자 이상 5글자 이하만 가능합니다.");
    }
}
