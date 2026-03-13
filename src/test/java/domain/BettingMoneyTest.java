package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.BlackjackException;
import exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {999, 0, -999, -2000})
    @DisplayName("1000 미만 숫자 입력 시 오류발생")
    void 숫자1000_미만_숫자_입력_시_오류발생(int invalidMoney) {
        //when & then
        assertThatThrownBy(() -> new BettingMoney(invalidMoney))
                .isInstanceOf(BlackjackException.class)
                .hasMessageContaining(ExceptionMessage.MONEY_INPUT_ERROR.message());
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 10000})
    @DisplayName("1000 이상 숫자 입력 시 정상작동")
    void 숫자1000_이상_입력_시_정상(int validMoney) {
        //when
        BettingMoney bettingMoney = new BettingMoney(validMoney);

        //then
        assertThat(bettingMoney.getMoney()).isEqualTo(validMoney);
    }

    @Test
    @DisplayName("돈의 단위는 100으로 한다.")
    void 단위는_1000이다() {
        // when & then
        assertThatThrownBy(() -> new BettingMoney(1020))
                .isInstanceOf(BlackjackException.class)
                .hasMessageContaining(ExceptionMessage.MONEY_UNIT_ERROR.message());
    }
}
