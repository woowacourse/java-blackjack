package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNameTest {

    @Test
    @DisplayName("플레이어의 이름으로 '딜러'가 들어오는 경우 예외처리하는 기능 테스트")
    void throwExceptionWhenPlayerNameIsDealerName() {
        assertThatThrownBy(() -> new PlayerName(DealerName.DEALER_NAME))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PlayerName.NAME_IS_DEALER_NAME_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("공백이 입력되면 예외 발생 테스트")
    void throwExceptionIfNameIsBlank() {
        assertThatThrownBy(() -> new PlayerName(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Name.BLANK_NAME_EXCEPTION_MESSAGE);
    }
}
