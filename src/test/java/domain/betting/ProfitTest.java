package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.game.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ProfitTest {

    @Nested
    @DisplayName("bet와 Result를 통해 수익을 생성한다.")
    class createProfit {
        @Test
        @DisplayName("Result가 플레이어승이면 베팅만큼 수익이 발생한다.")
        void createPlayerWin() {
            Bet bet = new Bet(20000);
            Profit profit = Profit.of(bet, Result.PLAYER_WIN);

            assertThat(profit.getMoney()).isEqualTo(bet.getMoney());
        }

        @Test
        @DisplayName("Result가 딜러승이면 베팅만큼 -수익이 발생한다.")
        void createDealerWin() {
            Bet bet = new Bet(20000);
            Profit profit = Profit.of(bet, Result.DEALER_WIN);

            assertThat(profit.getMoney()).isEqualTo(bet.getMoney() * -1);
        }

        @Test
        @DisplayName("Result가 Push면 수익이 0이다")
        void createPush() {
            Bet bet = new Bet(20000);
            Profit profit = Profit.of(bet, Result.PUSH);

            assertThat(profit.getMoney()).isEqualTo(0);
        }

        @Test
        @DisplayName("Result가 PlayerBlackJack이면 베팅의 1.5배의 수익이 발생한다.")
        void createPlayerBlackJack() {
            Bet bet = new Bet(20000);
            Profit profit = Profit.of(bet, Result.PLAYER_BLACK_JACK);

            assertThat(profit.getMoney()).isEqualTo(30000);
        }
    }
}
