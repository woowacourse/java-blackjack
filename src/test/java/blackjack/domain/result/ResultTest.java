package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    @DisplayName("블랙잭일 경우 베팅금을 1.5배로 계산해준다.")
    void calculateBetByBlackJack() {
        Result result = new BlackJack();
        assertThat(result.calculateBet(1000)).isEqualTo(1500);
    }

    @Test
    @DisplayName("패배일 경우 베팅금을 모두 잃는다.")
    void calculateBetByLose() {
        Result result = new Lose();
        assertThat(result.calculateBet(1000)).isEqualTo(-1000);
    }

    @Test
    @DisplayName("승리 경우 베팅금을 그대로 받는다.")
    void calculateBetByWin() {
        Result result = new Win();
        assertThat(result.calculateBet(1000)).isEqualTo(1000);
    }

    @Test
    @DisplayName("무승부 경우 계산 결과 0원이다.")
    void calculateBetByDraw() {
        Result result = new Draw();
        assertThat(result.calculateBet(1000)).isEqualTo(0);
    }

}