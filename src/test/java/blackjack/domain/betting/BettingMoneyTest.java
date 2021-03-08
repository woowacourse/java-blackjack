package blackjack.domain.betting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class BettingMoneyTest {
    @DisplayName("금액에 곱하기를 통해 새로운 금액 객체를 생성하는지")
    @ParameterizedTest
    @CsvSource({"1.5,1500", "2.0,2000", "3.3,3300", "0,0"})
    void multiplyTest(double ratio, long expectedMoneyAmount) {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000L);
        //when
        BettingMoney multipliedMoney = bettingMoney.multiply(ratio);
        //then
        assertThat(multipliedMoney).isEqualTo(new BettingMoney(expectedMoneyAmount));
    }

    @DisplayName("차액을 long 타입으로 구하는 메소드 확인")
    @ParameterizedTest
    @CsvSource({"1000,5000,-4000", "3000,0,3000","10000,5000,5000"})
    void calculateDifference(long first, long second, long expectedAmount) {
        //given
        BettingMoney firstMoney = new BettingMoney(first);
        BettingMoney secondMoney = new BettingMoney(second);
        //when
        long difference = firstMoney.subtract(secondMoney);
        //then
        assertThat(difference).isEqualTo(expectedAmount);
    }

    @DisplayName("금액이 음수일 때 예외가 발생하는지 확인")
    @Test
    void whenAmountIsNegative() {
        //given
        long negativeAmount = -100L;
        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BettingMoney(negativeAmount))
                .withMessage("배팅 금액은 음수일 수 없습니다.");

    }
}