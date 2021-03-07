package blakcjack.domain.name;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakcjack.domain.name.Name.EMPTY_NAME_ERROR;
import static blakcjack.domain.name.Name.NULL_NAME_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {
    @DisplayName("정상적인 이름 입력 객체 생성 성공")
    @Test
    void create() {
        final Name name = new Name("pobi");
        assertThat(name).isEqualTo(new Name("pobi"));
    }

    @DisplayName("null이 입력되면 예외 발생")
    @Test
    void create_null_throwError() {
        assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(IllegalPlayerNameException.class)
                .hasMessage(NULL_NAME_ERROR);
    }

    @DisplayName("빈 문자열이 입력되면 예외 발생")
    @Test
    void create_empty_throwError() {
        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalPlayerNameException.class)
                .hasMessage(EMPTY_NAME_ERROR);
    }
}