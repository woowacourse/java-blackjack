package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @Test
    void 게임_매니저를_생성한다() {
        //given
        //when
        final GameManager gameManager = GameManager.creat();
        //then
        assertThat(gameManager).isInstanceOf(GameManager.class);
    }
}
