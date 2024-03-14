package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어를 생성하면 초기 상태를 가진다")
    @Test
    public void create() {
        Player player = new Player("이상");

        assertThat(player.getState()).isInstanceOf(InitialState.class);
    }
}
