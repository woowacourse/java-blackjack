package blackjack.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @DisplayName("플레이어가 유효하지 않은 이름을 사용하려는 경우 예외가 발생한다.")
    @Test
    void occurExceptionIfPlayerTryToUseInvalidName() {
        final String invalidName = Name.INVALID_NAME;
        assertThatThrownBy(() -> new Name(invalidName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(invalidName + Name.ERROR_INVALID_NAME);
    }
}
