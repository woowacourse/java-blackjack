package blackjack.domain.participant;

import static blackjack.domain.participant.Name.EMPTY_NAME_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    @DisplayName("이름을 정상적으로 생성하는지 확인")
    void create() {
        Name name = new Name("필즈");

        assertThat(name).isNotNull();
    }

    @Test
    @DisplayName("입력한 이름이 동일한지 확인")
    void equalsName() {
        Name name = new Name("승팡");

        assertThat(name.getName()).isEqualTo("승팡");
    }

    @Test
    @DisplayName("이름에 빈값을 입력하면 예외를 던진다.")
    void validateEmptyName() {
        assertThatThrownBy(() -> new Name(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(EMPTY_NAME_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("이름에 null을 입력하면 예외를 던진다.")
    void validateNullName() {
        assertThatThrownBy(() -> new Name(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(EMPTY_NAME_EXCEPTION_MESSAGE);
    }
}
