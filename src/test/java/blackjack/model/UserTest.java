package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {

    @Test
    @DisplayName("올바른 이름이면 User 객체 생성")
    void test_success_when_valid_name() {
        String input = "pobi";

        User user = new User(input);

        assertThat(user.getName()).isEqualTo(input);
    }

    @Test
    @DisplayName("이름에 영어와 한글이 아닌 문자가 존재하는 경우 예외 발생")
    void test_fail_when_invalid_name() {
        String invalidInput = "james*";

        assertThatThrownBy(() -> new User(invalidInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "  ,  ,  "})
    @DisplayName("플레이어의 이름이 공백인 경우 예외 발생")
    void test_fail_when_name_is_null(String emptyName) {
        assertThatThrownBy(() -> new User(emptyName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
