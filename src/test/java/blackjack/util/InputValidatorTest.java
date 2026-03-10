package blackjack.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.common.error.ErrorCode;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            " player",
            "player ",
            " player "
    })
    @DisplayName("플레이어 이름이 공백으로 시작하거나 끝나면 예외 발생")
    void spaceInNameTest(String name) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validatePlayerNames(List.of(name)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorCode.NAME_STARTS_OR_ENDS_WITH_SPACE.getMessage());
    }


    @Test
    @DisplayName("중복 이름이 존재하면 예외 발생")
    void duplicatedNamesTest() {
        // given
        List<String> names = List.of("pobi", "jun", "pobi");

        // when & then
        assertThatThrownBy(() -> InputValidator.validatePlayerNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorCode.DUPLICATED_NAME.getMessage());
    }
}
