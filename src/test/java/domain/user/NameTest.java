package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameTest {

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThatCode(() -> new Name("이름"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("빈 이름이 있는 경우 예외 처리")
    void createPlayerWithEmptyName() {
        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 이름이 있습니다.");
    }

    @Test
    void getName() {
        Name name = new Name("이름");
        assertThat(name.getName()).isEqualTo("이름");
    }
}