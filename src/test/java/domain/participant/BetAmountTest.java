package domain.participant;

import domain.result.ResultStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BetAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void 배팅_금액이_0_이하이면_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> new BetAmount(amount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 배팅 금액은 최소 1원 입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 1000})
    void 배팅_금액이_1_이상이면_예외가_발생하지_않는다(int amount) {
        assertThatCode(() -> new BetAmount(amount)).doesNotThrowAnyException();
    }

    @Test
    void 결과별_수익을_계산한다() {
        BetAmount betAmount = new BetAmount(1000);

        int loseIncome = betAmount.calculateIncome(ResultStatus.LOSE);
        int winIncome = betAmount.calculateIncome(ResultStatus.WIN);
        int pushIncome = betAmount.calculateIncome(ResultStatus.PUSH);
        int blackJackIncome = betAmount.calculateIncome(ResultStatus.BLACK_JACK);

        assertAll(
            () -> assertThat(loseIncome).isEqualTo(-1000),
            () -> assertThat(winIncome).isEqualTo(1000),
            () -> assertThat(pushIncome).isEqualTo(0),
            () -> assertThat(blackJackIncome).isEqualTo(1500)
        );
    }
}
