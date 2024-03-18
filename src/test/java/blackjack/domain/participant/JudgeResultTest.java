package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeResultTest {

    @DisplayName("질 경우, 수익금을 계산한다.")
    @Test
    void whenLose() {
        Money money = new Money(1000);
        JudgeResult judgeResult = JudgeResult.LOSE;

        Money result = judgeResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(-1000);
    }

    @DisplayName("이길 경우, 수익금을 계산한다.")
    @Test
    void whenWin() {
        Money money = new Money(1000);
        JudgeResult judgeResult = JudgeResult.WIN;

        Money result = judgeResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(1000);
    }

    @DisplayName("블랙잭으로 이길 경우, 수익금을 계산한다.")
    @Test
    void whenBlackjackWin() {
        Money money = new Money(1000);
        JudgeResult judgeResult = JudgeResult.BLACKJACK_WIN;

        Money result = judgeResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(1500);
    }

    @DisplayName("무승부일 경우, 수익금을 계산한다.")
    @Test
    void whenTie() {
        Money money = new Money(1000);
        JudgeResult judgeResult = JudgeResult.TIE;

        Money result = judgeResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(0);
    }
}
