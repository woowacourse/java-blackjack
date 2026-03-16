package blackjack.model.user;

import static blackjack.model.user.Username.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UsernameTest {

    @ParameterizedTest
    @ValueSource(strings = {"pobi", "jason"})
    @DisplayName("Username 정상 생성")
    void create_username_success(String username) {
        //when & then
        assertDoesNotThrow(() -> new Username(username));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("이름이 공백인 경우 예외 발생")
    void create_username_fail_when_empty_name(String emptyName) {
        //when & then
        assertThatThrownBy(() -> new Username(emptyName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_EMPTY_INPUT);
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi!", "jason1"})
    @DisplayName("이름에 영어와 한글 외의 문자가 존재할 경우 예외 발생")
    void create_username_fail_when_invalid_name(String invalidName) {
        //when & then
        assertThatThrownBy(() -> new Username(invalidName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_INVALID_PLAYER_NAME);
    }
}