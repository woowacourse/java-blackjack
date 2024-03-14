package blackjack.domain.judgement;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.common.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgementResultTest {

    @DisplayName("질 경우, 수익금을 계산한다.")
    @Test
    void whenLose() {
        Money money = new Money(1000);
        JudgementResult judgementResult = JudgementResult.LOSE;

        Money result = judgementResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(-1000);
    }

    @DisplayName("이길 경우, 수익금을 계산한다.")
    @Test
    void whenWin() {
        Money money = new Money(1000);
        JudgementResult judgementResult = JudgementResult.WIN;

        Money result = judgementResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(1000);
    }

    @DisplayName("블랙잭으로 이길 경우, 수익금을 계산한다.")
    @Test
    void whenBlackjackWin() {
        Money money = new Money(1000);
        JudgementResult judgementResult = JudgementResult.BLACKJACK_WIN;

        Money result = judgementResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(1500);
    }

    @DisplayName("무승부일 경우, 수익금을 계산한다.")
    @Test
    void whenTie() {
        Money money = new Money(1000);
        JudgementResult judgementResult = JudgementResult.TIE;

        Money result = judgementResult.calculateProfit(money);

        assertThat(result.getAmount()).isEqualTo(0);
    }
}
