package blackjack.domain.judgement;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.PlayerMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgementResultTest {

    @DisplayName("질 경우, 수익금을 계산한다.")
    @Test
    void whenLose() {
        PlayerMoney money = new PlayerMoney(1000);
        JudgementResult judgementResult = JudgementResult.LOSE;

        PlayerMoney result = judgementResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(-1000);
    }

    @DisplayName("이길 경우, 수익금을 계산한다.")
    @Test
    void whenWin() {
        PlayerMoney money = new PlayerMoney(1000);
        JudgementResult judgementResult = JudgementResult.WIN;

        PlayerMoney result = judgementResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(1000);
    }

    @DisplayName("블랙잭으로 이길 경우, 수익금을 계산한다.")
    @Test
    void whenBlackjackWin() {
        PlayerMoney money = new PlayerMoney(1000);
        JudgementResult judgementResult = JudgementResult.BLACKJACK_WIN;

        PlayerMoney result = judgementResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(1500);
    }

    @DisplayName("무승부일 경우, 수익금을 계산한다.")
    @Test
    void whenTie() {
        PlayerMoney money = new PlayerMoney(1000);
        JudgementResult judgementResult = JudgementResult.TIE;

        PlayerMoney result = judgementResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(0);
    }
}
