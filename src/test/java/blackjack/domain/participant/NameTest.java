package blackjack.domain.participant;

import static blackjack.domain.participant.Name.*;
import static org.assertj.core.api.Assertions.*;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

        assertThat(name.getValue()).isEqualTo("승팡");
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

    @ParameterizedTest
    @ValueSource(strings = {"a!", " @가", "(A)"})
    @DisplayName("유저의 이름에 한글, 알파벳, 공백, 숫자, 언더바 이외의 문자는 허용되지 않는다")
    void validateSpecialCharacter(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_ALLOWED_SPECIFIC_CHARACTER_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"seong woo", "seong_woo", "player1", "21children", "_"})
    @DisplayName("유저의 이름에 한글, 알파벳, 공백, 숫자, 언더바의 허용되는 문자 입력시 이름을 생성할 수 있다")
    void does_not_contain_SpecialCharacter(String name) {
        Assertions.assertDoesNotThrow(()-> new Name(name));
    }
}
