package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @Test
    @DisplayName("숫자가 아닌 값으로 생성 시도 시 오류 발생")
    void of_fail_not_number() {
        //given
        String notNumber = "NaN";

        //when
        assertThatThrownBy(
                () -> BetAmount.of(notNumber)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ONLY_NUMBER.getMessage());
    }

    @Test
    @DisplayName("양수가 아니면 오류 발생")
    void of_fail_not_positive_num() {
        //given
        String notPosNum = "-1";
        //when
        assertThatThrownBy(
                () -> BetAmount.of(notPosNum)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ZERO_MINUS_MONEY.getMessage());

    }

    @Test
    @DisplayName("양수면 정상 생성")
    void of_good() {
        String posNum = "10000";

        assertDoesNotThrow(
                () -> BetAmount.of(posNum)
        );
    }

    @Test
    @DisplayName("0이면 베팅을 안한 것으로 친다")
    void zero_mean_not_bet_yet() {
        //given
        String zero = "0";

        //when
        BetAmount testBetAmount = BetAmount.of(zero);

        //then
        assertTrue(testBetAmount.isBetPlaced());
    }
}