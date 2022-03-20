package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어는 1명 이상이어야 한다.")
    @Test
    void 플레이어들_생성() {
        assertDoesNotThrow(() -> new Players(Map.of("mat", "10000")));
    }

    @DisplayName("플레이어는 1명 이상이어야 한다. 1명 보다 작은 경우 예외를 던진다.")
    @Test
    void 플레이어들_생성_실패() {
        assertThatThrownBy(() -> new Players(Map.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어는 최소 1명입니다.");
    }
}