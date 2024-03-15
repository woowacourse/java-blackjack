package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @DisplayName("플레이어의 수가 최소 1명 최대 8명으로 이루어져 있지 않은 경우 예외가 발생한다.")
    @Test
    void validateSize() {
        List<Player> players = List.of();
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 최소 1명 최대 8명입니다 : 현재 %d명".formatted(0));
    }
}
