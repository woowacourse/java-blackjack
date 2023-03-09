package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultsTest {
    @DisplayName("Player의 게임 결과를 저장하고 조회한다.")
    @Test
    void 게임결과_저장_및_조회() {
        PlayerResults playerResults = new PlayerResults();
        Player player = new Player("name");
        playerResults.save(player, Result.WIN);
        assertThat(playerResults.findByPlayer(player)).isEqualTo(Result.WIN);
    }
}
