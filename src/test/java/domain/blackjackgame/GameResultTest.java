package domain.blackjackgame;

import static fixture.ParticipantFixture.플레이어;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GameResultTest {
    @Test
    void 딜러의_결과는_플레이어의_결과와_반대이다() {
        GameResult gameResult = new GameResult();
        gameResult.record(플레이어("프린"), 1000);

        int dealerResult = gameResult.getDealerResult();

        assertThat(dealerResult).isEqualTo(-1000);
    }
}
