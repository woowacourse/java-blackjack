package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "", "\n"})
    @NullSource
    @DisplayName("이름이 공백이면 예외가 발생해야 한다.")
    void validateBlankName(String input) {
        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Name(input);
        }).withMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 5글자를 초과하면 예외가 발생해야 한다.")
    void validateNameLength() {
        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Name("123456");
        }).withMessage("[ERROR] 이름은 5글자를 넘을 수 없습니다.");
    }
}
