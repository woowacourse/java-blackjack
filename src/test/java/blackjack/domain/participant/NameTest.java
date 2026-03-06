package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("정상적인 이름으로 생성에 성공한다")
    void constructor_succeeds_whenValidName() {
        Name name = new Name("pobi");

        assertThat(name.getValue()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("이름이 빈 문자열이면 예외가 발생한다")
    void constructor_throwsException_whenNameIsBlank() {
        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 null이면 예외가 발생한다")
    void constructor_throwsException_whenNameIsNull() {
        assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 공백 문자열이면 예외가 발생한다")
    void constructor_throwsException_whenNameIsWhitespace() {
        assertThatThrownBy(() -> new Name("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }
}
