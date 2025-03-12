package blackjack.domain.gambler;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @DisplayName("이름이 6글자 이상인 경우 예외를 던진다.")
    @Test
    void validateNameLengthIsOverSix() {
        assertThatThrownBy(() -> new Name("라젤라젤라젤"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 최소 2글자, 최대 5글자로 제한 한다.");
    }

    @DisplayName("이름이 1글자 이하인 경우 예외를 던진다")
    @Test
    void validateNameLengthIsBelowOne() {
        assertThatThrownBy(() -> new Name("라"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 최소 2글자, 최대 5글자로 제한 한다.");
    }

    @DisplayName("이름에 공백이 포함된 경우 예외를 던진다.")
    @Test
    void validateNameHasBlank() {
        assertThatThrownBy(() -> new Name("라 젤"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름에 공백을 포함할 수 없다.");
    }
}
