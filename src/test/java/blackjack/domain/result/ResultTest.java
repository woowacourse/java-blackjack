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
    @DisplayName("버스트일 경우 베팅금을 모두 잃는다.")
    void calculateBetByBust() {
        Result result = new Lose();
        assertThat(result.calculateBet(1000)).isEqualTo(-1000);
    }

}