package model.money;

import static model.user.GameState.BLACKJACK;
import static model.user.GameState.LOSE;
import static model.user.GameState.TIE;
import static model.user.GameState.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BetTest {

    @Test
    @DisplayName("돈이 추가가 된다.")
    void whenAddMoney_thenSuccess() {
        // given
        final Bet bet = new Bet(10_000);
        final Bet addBet = new Bet(10_000);

        // when
        final Bet result = bet.add(addBet);

        // then
        assertThat(result).isEqualTo(new Bet(20_000));
    }

    @Test
    @DisplayName("게임에서 블랙잭으로 이길 경우 돈의 값이 1.5배로 바뀐다.")
    void whenBlackJack_thenReturnBlackJackMoney() {
        // given
        final Bet bet = new Bet(10_000);

        // when
        final Bet blackJack = bet.blackJack();

        // then
        assertThat(blackJack).isEqualTo(new Bet(15_000));
    }

    @Nested
    @DisplayName("Result에 따라 돈을 계산한다")
    class calculateByResult {

        @Test
        @DisplayName("게임이 블랙잭이면 블랙잭 머니로 바뀐다.")
        void whenBlackGame_thenReturnBlackMoney() {
            // given
            final Bet bet = new Bet(10_000);

            // when
            final Bet lose = bet.calculateBet(BLACKJACK);

            // then
            assertThat(lose).isEqualTo(new Bet(15_000));
        }

        @Test
        @DisplayName("게임에서 질 경우 돈의 값이 마이너스로 바뀐다.")
        void whenLoseGame_thenReturnLoseMoney() {
            // given
            final Bet bet = new Bet(10_000);

            // when
            final Bet lose = bet.calculateBet(LOSE);

            // then
            assertThat(lose).isEqualTo(new Bet(-10_000));
        }

        @Test
        @DisplayName("게임에서 비길 경우 돈을 돌려 받는다.")
        void whenTie_thenReturnMoney() {
            // given
            final Bet bet = new Bet(10_000);

            // when
            final Bet tie = bet.calculateBet(TIE);

            // then
            assertThat(tie).isEqualTo(new Bet(0));
        }

        @Test
        @DisplayName("게임에서 이길 경우 돈을 돌려 받는다.")
        void whenWin_thenReturnMoney() {
            // given
            final Bet bet = new Bet(10_000);

            // when
            final Bet tie = bet.calculateBet(WIN);

            // then
            assertThat(tie).isEqualTo(new Bet(10_000));
        }
    }
}
