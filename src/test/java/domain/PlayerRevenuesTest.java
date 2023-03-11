package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerRevenuesTest {
    @DisplayName("Player의 게임 결과를 저장하고 조회한다.")
    @Test
    void 게임결과_저장_및_조회() {
        PlayerRevenues playerRevenues = new PlayerRevenues();
        Player player = new Player("name", 1000);
        final int result = 10000;
        playerRevenues.save(player, result);
        assertThat(playerRevenues.findByPlayer(player)).isEqualTo(result);
    }

}
