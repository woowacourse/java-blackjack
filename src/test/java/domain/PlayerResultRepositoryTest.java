package domain;

import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerResultRepositoryTest {
    @DisplayName("Player의 게임 결과를 저장하고 조회한다.")
    @Test
    void 게임결과_저장_및_조회() {
        PlayerResultRepository playerResultRepository = new PlayerResultRepository();
        Player player = new Player("name");
        playerResultRepository.save(player, Result.WIN);
        assertThat(playerResultRepository.findByPlayer(player)).isEqualTo(Result.WIN);
    }
}