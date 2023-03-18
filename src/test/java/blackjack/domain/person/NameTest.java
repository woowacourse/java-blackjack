package blackjack.domain.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NameTest {

    @ParameterizedTest(name = "이름이 \"{0}\"일 때")
    @ValueSource(strings = {" ", "  ", "", "\n"})
    @DisplayName("이름이 공백이면 예외가 발생해야 한다.")
    void validateBlankName(String input) {
        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(input)).withMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 5글자를 초과하면 예외가 발생해야 한다.")
    void validateNameLength() {
        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name("123456"))
                .withMessage("[ERROR] 이름은 5글자를 넘을 수 없습니다.");
    }

    @Test
    @DisplayName("Name이 정상적으로 생성되어야 한다.")
    void create_success() {
        // given
        Name name = new Name("123");

        // expect
        assertThat(name.getName())
                .isEqualTo("123");
    }

}