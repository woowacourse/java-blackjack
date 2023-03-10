package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NameTest {

    @ParameterizedTest(name = "input : {0}")
    @NullAndEmptySource
    @DisplayName("이름은 null 또는 빈값을 입력할 수 없다.")
    void test_validate_null_or_empty(String input) {
        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(input))
                .withMessage("이름은 빈값을 입력할 수 없습니다.");
    }


    @ParameterizedTest(name = "input : {0}")
    @ValueSource(strings = {" ", " 채채"})
    @DisplayName("이름은 공백을 포함할 수 없다.")
    void test_validate_has_blank(String input) {
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(input))
                .withMessage("이름은 공백을 포함할 수 없습니다.");
    }

    @ParameterizedTest(name = "input : {0}")
    @ValueSource(strings = {"123456789100"})
    @DisplayName("이름은 공백을 포함할 수 없다.")
    void test_validate_range(String input) {
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(input))
                .withMessage("각 이름의 수가 1이상 10이하여야 합니다.");
    }
}
