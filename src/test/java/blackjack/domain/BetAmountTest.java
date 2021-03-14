package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BetAmountTest {

    @Test
    @DisplayName("같은 값을 가지면 같은 객체로 인식하는 지 테스트")
    public void equals() {
        assertThat(new BetAmount(1)).isEqualTo(new BetAmount(1));
    }

    @Test
    @DisplayName("특정 a값을 넣었을 때, 음수로 변환해서 -a를 리턴하는 지 테스트")
    public void toNegative() {
        BetAmount betAmount = new BetAmount(1);
        assertThat(betAmount.toNegative()).isEqualTo(new BetAmount(-1));
    }

    @ParameterizedTest(name = "{displayName}")
    @DisplayName("특정 a값을 넣었을 때, 절반으로 변환해주는 지 테스트")
    @CsvSource(value = {"1:0.5", "5000:2500"}, delimiter = ':')
    public void toHalf(double input, double result) {
        BetAmount betAmount = new BetAmount(input);
        assertThat(betAmount.toHalf()).isEqualTo(new BetAmount(result));
    }

    @ParameterizedTest(name = "{displayName}")
    @DisplayName("betAmount끼리 덧셈이 정상적으로 되는 지 테스트")
    @CsvSource(value = {"1000:2000:3000", "500:5000:5500"}, delimiter = ':')
    public void add(double betAmount, double otherBetAmount, double sumResult) {
        BetAmount betAmount1 = new BetAmount(betAmount);
        BetAmount betAmount2 = new BetAmount(otherBetAmount);
        assertThat(betAmount1.add(betAmount2)).isEqualTo(new BetAmount(sumResult));
    }
}