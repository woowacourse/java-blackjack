package blackjack.domain.gamer;

import blackjack.domain.betting.Betting;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingTest {

    @DisplayName("0 이하의 배팅 금액이 입력되면 예외를 발생시킨다.")
    @Test
    void validateMoneyRange() {
        //given
        String input = "-1";

        // when, then
        assertThatThrownBy(() -> new Betting(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자가 아닌 배팅 금액이 입력되면 예외를 발생시킨다.")
    @Test
    void validateMoneyInput() {
        //given
        String input = "a";

        // when, then
        assertThatThrownBy(() -> new Betting(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액 계산 결과를 반환한다.")
    @Test
    void calculateBettingMoney() {
        //given
        Betting betting = new Betting("1000");

        // when
        betting.calculateBettingMoney(1.5);

        // then
        assertThat(betting.getBettingMoney()).isEqualTo(1500);
    }
}
