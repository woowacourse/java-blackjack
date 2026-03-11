package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vo.GameResult;

public class GameJudgeTest {

    private GameJudge gameJudge;

    @BeforeEach
    void setUp() {
        gameJudge = new GameJudge();
    }

    @Test
    void 유저_버스트_BUST() {
        assertThat(gameJudge.judge(17, 22, false)).isEqualTo(GameResult.BUST);
    }

    @Test
    void 딜러_버스트_유저_WIN() {
        assertThat(gameJudge.judge(22, 18, false)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 유저_점수_높으면_WIN() {
        assertThat(gameJudge.judge(15, 18, false)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 유저_점수_낮으면_LOSE() {
        assertThat(gameJudge.judge(18, 15, false)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 동점이면_PUSH() {
        assertThat(gameJudge.judge(18, 18, false)).isEqualTo(GameResult.PUSH);
    }

    @Test
    void 유저_블랙잭_BLACKJACK() {
        assertThat(gameJudge.judge(17, 21, true)).isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    void 양쪽_모두_블랙잭_PUSH() {
        assertThat(gameJudge.judge(21, 21, true)).isEqualTo(GameResult.PUSH);
    }
}
