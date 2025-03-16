package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @DisplayName("베팅 금액이 1원 미만이라면 예외가 발생한다.")
    @Test
    void validate() {
        //given
        long amount = -1L;

        //when //then
        assertThatThrownBy(() -> BetAmount.from(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @DisplayName("베팅 금액이 1원 이상이라면 예외가 발생하지 않는다.")
    @Test
    void validatePass() {
        //given
        long amount = 1L;

        //when //then
        assertThatCode(() -> BetAmount.from(amount))
                .doesNotThrowAnyException();
    }

    @DisplayName("블랙잭은 베팅금액의 1.5배를 돌려준다.")
    @Test
    void blackjackProfit() {
        //given
        BetAmount betAmount = BetAmount.from(1000L);

        //when
        Long actual = betAmount.calculateBlackjackProfit();

        //then
        assertThat(actual).isEqualTo(1500);
    }

    @DisplayName("자신의 베팅 금액에 수익 금액을 더할 수 있다.")
    @Test
    void plus() {
        //given
        BetAmount betAmount = BetAmount.from(1000L);

        //when
        Long actual = betAmount.plus(1000L);

        //then
        assertThat(actual).isEqualTo(2000L);
    }

}
