package domain.blackjackgame;

import static fixture.ParticipantFixture.플레이어;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GameResultTest {
    @Test
    void 딜러의_수익은_플레이어의_수익과_반대이다() {
        GameResult gameResult = new GameResult();
        int profit = 1000;
        gameResult.record(플레이어("프린"), profit);

        int dealerResult = gameResult.getDealerResult();

        assertThat(dealerResult).isEqualTo(-profit);
    }

    @Test
    void 플레이어의_수익의_합이_0이면_딜러의_수익도_0이다() {
        GameResult gameResult = new GameResult();
        gameResult.record(플레이어("프린"), 1000);
        gameResult.record(플레이어("뽀로로"), -1000);

        int dealerResult = gameResult.getDealerResult();

        assertThat(dealerResult).isEqualTo(0);
    }
}
