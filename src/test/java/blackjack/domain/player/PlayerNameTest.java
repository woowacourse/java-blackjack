package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNameTest {

    @DisplayName("이름은 빈 문자열일 수 없다.")
    @Test
    void testCreateEmptyPlayerName() {
        // when & then
        assertThatThrownBy(() -> new PlayerName(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어 이름을 생성한다.")
    @Test
    void testCreatePlayerName() {
        // when & then
        assertThatCode(() -> new PlayerName("a"))
                .doesNotThrowAnyException();
    }
}
