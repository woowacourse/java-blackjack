package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameStatusTest {

    @DisplayName("게임이 시작되었는지 확인한다.")
    @Test
    void checkIsStart() {
        GameStatus gameStatus = new GameStatus(true);
        assertThat(gameStatus.isStart()).isTrue();
    }
}
