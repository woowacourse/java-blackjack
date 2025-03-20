package blackjack.object.gambler;

import static blackjack.object.view.ErrorMessage.INVALID_PLAYER_NAME_FORMAT;
import static blackjack.object.view.ErrorMessage.INVALID_PLAYER_NAME_LENGTH;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @DisplayName("이름이 6글자 이상인 경우 예외를 던진다.")
    @Test
    void validateNameLengthIsOverSix() {
        assertThatThrownBy(() -> new Name("라젤라젤라젤"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PLAYER_NAME_LENGTH.getMessage());
    }

    @DisplayName("이름이 1글자 이하인 경우 예외를 던진다")
    @Test
    void validateNameLengthIsBelowOne() {
        assertThatThrownBy(() -> new Name("라"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PLAYER_NAME_LENGTH.getMessage());
    }

    @DisplayName("이름에 공백이 포함된 경우 예외를 던진다.")
    @Test
    void validateNameHasBlank() {
        assertThatThrownBy(() -> new Name("라 젤"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PLAYER_NAME_FORMAT.getMessage());
    }
}
