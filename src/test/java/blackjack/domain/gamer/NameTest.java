package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class NameTest {

    @DisplayName("플레이어가 유효하지 않은 이름을 사용하려는 경우 예외가 발생한다.")
    @Test
    void occurExceptionIfPlayerTryToUseInvalidName() {
        final String name = Name.INVALID_NAME;
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(name + Name.ERROR_INVALID_NAME);
    }

    @DisplayName("플레이어 이름이 null이거나 공백인 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void occurExceptionIfPlayerNameIsNullOrBlank(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Name.ERROR_PLAYER_NAME_IS_NULL_OR_BLANK);
    }
}
