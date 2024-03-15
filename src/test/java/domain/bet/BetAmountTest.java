package domain.bet;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @Test
    @DisplayName("배팅 금액이 백만원을 초과하면 예외가 발생한다")
    void betMaxRange() {
        Assertions.assertThatCode(()->new BetAmount(1000001)).isInstanceOf(IllegalArgumentException.class);

}
