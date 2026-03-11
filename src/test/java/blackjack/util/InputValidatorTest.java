package blackjack.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    @DisplayName("플레이어 이름이 공백으로 시작하거나 끝나면 예외가 발생한다.")
    void spaceInNameTest(String name) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validatePlayerNames(List.of(name)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름이 공백으로 시작하거나 끝납니다.");
    }


    @Test
    @DisplayName("중복 이름이 존재하면 예외가 발생한다.")
    void duplicatedNamesTest() {
        // given
        List<String> names = List.of("pobi", "jun", "pobi");

        // when & then
        assertThatThrownBy(() -> InputValidator.validatePlayerNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 플레이어 이름이 존재합니다.");
    }

    @Test
    @DisplayName("이름이 하나도 입력되지 않으면 예외가 발생한다.")
    void nullNames() {
        assertThatThrownBy(() -> InputValidator.validatePlayerNames(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("names가 null입니다.");
    }

    @Test
    @DisplayName("배팅 금액이 자연수가 아니면 예외가 발생한다.")
    void not_natural_number() {
        assertThatThrownBy(() -> InputValidator.validateBetAmount(-100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자연수가 아닙니다.");
    }

    @Test
    @DisplayName("배팅 금액이 100원 단위가 아니면 예외가 발생한다.")
    void not_100() {
        assertThatThrownBy(() -> InputValidator.validateBetAmount(101))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("100원 단위가 아닙니다.");
    }
}