package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.game.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ProfitRateTest {

    @Nested
    @DisplayName("bet와 Result를 통해 수익률을 생성한다.")
    class createProfitRate {
        @Test
        @DisplayName("Result가 플레이어승이면 베팅만큼 수익이 발생한다.")
        void createPlayerWin() {
            ProfitRate profitRate = ProfitRate.from(Result.PLAYER_WIN);

            assertThat(profitRate.get()).isEqualTo(1.0);
        }

        @Test
        @DisplayName("Result가 딜러승이면 베팅만큼 -수익이 발생한다.")
        void createDealerWin() {
            ProfitRate profitRate = ProfitRate.from(Result.DEALER_WIN);

            assertThat(profitRate.get()).isEqualTo(-1.0);
        }

        @Test
        @DisplayName("Result가 Push면 수익이 0이다")
        void createPush() {
            ProfitRate profitRate = ProfitRate.from(Result.PUSH);

            assertThat(profitRate.get()).isEqualTo(0);
        }

        @Test
        @DisplayName("Result가 PlayerBlackJack이면 베팅의 1.5배의 수익이 발생한다.")
        void createPlayerBlackJack() {
            ProfitRate profitRate = ProfitRate.from(Result.PLAYER_BLACK_JACK);

            assertThat(profitRate.get()).isEqualTo(1.5);
        }
    }
}
