package domain.blackjackgame;

import static fixture.ParticipantFixture.플레이어;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class GameResultTest {
    @Test
    void 딜러의_수익은_플레이어의_수익과_반대이다() {
        GameResult gameResult = new GameResult();
        BigDecimal profit = BigDecimal.valueOf(1000);
        gameResult.record(플레이어("프린"), profit);

        BigDecimal dealerResult = gameResult.getDealerResult();

        assertThat(dealerResult).isEqualTo(profit.negate());
    }

    @Test
    void 플레이어의_수익의_합이_0이면_딜러의_수익도_0이다() {
        GameResult gameResult = new GameResult();
        gameResult.record(플레이어("프린"), BigDecimal.valueOf(1000));
        gameResult.record(플레이어("뽀로로"), BigDecimal.valueOf(-1000));

        BigDecimal dealerResult = gameResult.getDealerResult();

        assertThat(dealerResult).isEqualTo(BigDecimal.ZERO);
    }
}
